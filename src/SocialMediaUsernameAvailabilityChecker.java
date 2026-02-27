import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocialMediaUsernameAvailabilityChecker {
    private final Map<String, Integer> users = new ConcurrentHashMap<>();
    private final Map<String, Integer> attempts = new ConcurrentHashMap<>();

    private int nextUserId = 1;

    public static void main(String[] args) {
        SocialMediaUsernameAvailabilityChecker system = new SocialMediaUsernameAvailabilityChecker();
        system.register("john_doe");
        system.register("admin");

        System.out.println("john_doe available? " + system.checkAvailability("john_doe"));

        System.out.println("jane_smith available? " + system.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe: " + system.suggestAlternatives("john_doe"));
        for (int i = 0; i < 5; i++) {
            system.checkAvailability("admin");
        }

        System.out.println("Most attempted username: " + system.getMostAttempted());
    }

    public boolean checkAvailability(String username) {
        attempts.merge(username, 1, Integer::sum);
        return !users.containsKey(username);
    }

    public synchronized boolean register(String username) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, nextUserId++);
        return true;
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        if (!users.containsKey(username)) {
            suggestions.add(username);
            return suggestions;
        }
        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;
            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }
        if (username.contains("_")) {
            String dotVersion = username.replace("_", ".");
            if (!users.containsKey(dotVersion)) {
                suggestions.add(dotVersion);
            }
        }

        return suggestions;
    }

    public String getMostAttempted() {
        String result = null;
        int max = 0;

        for (Map.Entry<String, Integer> entry : attempts.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
}