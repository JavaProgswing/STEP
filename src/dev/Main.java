package dev;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class TokenBucket {

    private final int capacity;
    private final int refillRatePerSec;

    private double tokens;
    private long lastRefill;

    public TokenBucket(int cap, int ratePerSec) {
        capacity = cap;
        refillRatePerSec = ratePerSec;
        tokens = cap;
        lastRefill = System.nanoTime();
    }

    public synchronized boolean allow() {
        refill();

        if (tokens >= 1) {
            tokens -= 1;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double seconds = (now - lastRefill) / 1e9;

        double add = seconds * refillRatePerSec;
        if (add > 0) {
            tokens = Math.min(capacity, tokens + add);
            lastRefill = now;
        }
    }

    public synchronized int remaining() {
        refill();
        return (int) tokens;
    }
}

class RateLimiter {

    private final Map<String, TokenBucket> buckets = new ConcurrentHashMap<>();

    private final int LIMIT = 1000;
    private final int REFILL_PER_SEC = 1000 / 3600; // per hour

    public boolean check(String client) {
        TokenBucket b = buckets.computeIfAbsent(client, k -> new TokenBucket(LIMIT, REFILL_PER_SEC));
        return b.allow();
    }

    public int remaining(String client) {
        return buckets.getOrDefault(client, new TokenBucket(LIMIT, REFILL_PER_SEC)).remaining();
    }
}

public class Main {

    public static void main(String[] args) {

        RateLimiter rl = new RateLimiter();

        String client = "abc123";

        for (int i = 0; i < 1100; i++) {
            if (rl.check(client)) {
                System.out.println("Allowed → remaining: " + rl.remaining(client));
            } else {
                System.out.println("DENIED → limit reached");
                break;
            }
        }
    }
}
