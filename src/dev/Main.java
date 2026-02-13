package dev;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

class UsernameService {

    // username -> userId (simulates 10M users)
    private final ConcurrentHashMap<String, Integer> users = new ConcurrentHashMap<>();

    // username -> attempt count
    private final ConcurrentHashMap<String, Integer> attempts = new ConcurrentHashMap<>();

    private int userIdCounter = 1;

    // O(1) availability check
    public boolean checkAvailability(String username) {
        attempts.merge(username, 1, Integer::sum);
        return !users.containsKey(username);
    }

    // register username if available
    public boolean register(String username) {
        if (!checkAvailability(username)) return false;
        users.put(username, userIdCounter++);
        return true;
    }

    // suggestions if taken
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        if (!users.containsKey(username)) {
            suggestions.add(username);
            return suggestions;
        }

        // append numbers
        for (int i = 1; i <= 5; i++) {
            String candidate = username + i;
            if (!users.containsKey(candidate)) {
                suggestions.add(candidate);
            }
        }

        // replace underscore with dot
        if (username.contains("_")) {
            String dotVersion = username.replace("_", ".");
            if (!users.containsKey(dotVersion)) {
                suggestions.add(dotVersion);
            }
        }

        // random suffix
        for (int i = 0; i < 3; i++) {
            String candidate = username + "_" + new Random().nextInt(999);
            if (!users.containsKey(candidate)) {
                suggestions.add(candidate);
            }
        }

        return suggestions;
    }

    // most attempted username
    public String getMostAttempted() {
        String maxUser = null;
        int max = 0;

        for (Map.Entry<String, Integer> e : attempts.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                maxUser = e.getKey();
            }
        }

        return maxUser + " (" + max + " attempts)";
    }
}

public class Main {
    public static void main(String[] args) {

        UsernameService service = new UsernameService();

        // simulate existing users
        service.register("john_doe");
        service.register("admin");
        service.register("admin1");

        System.out.println("john_doe available? " + service.checkAvailability("john_doe"));

        System.out.println("jane_smith available? " + service.checkAvailability("jane_smith"));

        System.out.println("Suggestions for john_doe:");
        System.out.println(service.suggestAlternatives("john_doe"));

        // simulate many attempts
        for (int i = 0; i < 10000; i++) {
            service.checkAvailability("admin");
        }

        System.out.println("Most attempted: " + service.getMostAttempted());
    }
}

