package dev;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class UsernameService {

    // username -> userId
    private final ConcurrentHashMap<String, Integer> users = new ConcurrentHashMap<>();

    // username -> attempts
    private final ConcurrentHashMap<String, AtomicInteger> attempts = new ConcurrentHashMap<>();

    private final AtomicInteger idGen = new AtomicInteger(1);

    // O(1)
    public boolean checkAvailability(String username) {
        attempts.computeIfAbsent(username, k -> new AtomicInteger(0)).incrementAndGet();
        return !users.containsKey(username);
    }

    public boolean register(String username) {
        if (!checkAvailability(username)) return false;
        users.put(username, idGen.getAndIncrement());
        return true;
    }

    public List<String> suggestAlternatives(String username) {
        List<String> res = new ArrayList<>();

        if (!users.containsKey(username)) {
            res.add(username);
            return res;
        }

        // numeric suffix
        for (int i = 1; i <= 5; i++) {
            String c = username + i;
            if (!users.containsKey(c)) res.add(c);
        }

        // replace underscore
        if (username.contains("_")) {
            String dot = username.replace("_", ".");
            if (!users.containsKey(dot)) res.add(dot);
        }

        // random suffix
        for (int i = 0; i < 3; i++) {
            String c = username + "_" + new Random().nextInt(999);
            if (!users.containsKey(c)) res.add(c);
        }

        return res;
    }

    public String getMostAttempted() {
        String best = null;
        int max = 0;

        for (var e : attempts.entrySet()) {
            int val = e.getValue().get();
            if (val > max) {
                max = val;
                best = e.getKey();
            }
        }
        return best + " (" + max + " attempts)";
    }
}

public class Main {
    public static void main(String[] args) {

        UsernameService s = new UsernameService();

        s.register("john_doe");
        s.register("admin");

        System.out.println(s.checkAvailability("john_doe"));
        System.out.println(s.checkAvailability("jane_smith"));

        System.out.println(s.suggestAlternatives("john_doe"));

        for (int i = 0; i < 10000; i++) s.checkAvailability("admin");

        System.out.println(s.getMostAttempted());
    }
}
