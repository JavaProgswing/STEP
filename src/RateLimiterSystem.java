import java.util.*;

public class RateLimiterSystem {
    private static class TokenBucket {
        double tokens;
        long lastRefillTime;
        final double maxTokens;
        final double refillRate; // tokens per second

        TokenBucket(double maxTokens, double refillRate) {
            this.tokens = maxTokens;
            this.maxTokens = maxTokens;
            this.refillRate = refillRate;
            this.lastRefillTime = System.currentTimeMillis();
        }

        synchronized boolean allowRequest() {
            refill();

            if (tokens >= 1) {
                tokens -= 1;
                return true;
            }
            return false;
        }

        synchronized int getRemainingTokens() {
            refill();
            return (int) tokens;
        }

        private void refill() {
            long now = System.currentTimeMillis();
            double secondsPassed = (now - lastRefillTime) / 1000.0;

            double newTokens = secondsPassed * refillRate;
            tokens = Math.min(maxTokens, tokens + newTokens);

            lastRefillTime = now;
        }
    }
    private final Map<String, TokenBucket> clients = new HashMap<>();
    private final double MAX_REQUESTS = 1000;
    private final double REFILL_RATE = 1000.0 / 3600.0;

    public synchronized String checkRateLimit(String clientId) {
        TokenBucket bucket = clients.computeIfAbsent(
                clientId,
                id -> new TokenBucket(MAX_REQUESTS, REFILL_RATE)
        );

        if (bucket.allowRequest()) {
            return "Allowed. Remaining: " +
                    bucket.getRemainingTokens();
        } else {
            return "Denied. Rate limit exceeded.";
        }
    }

    public synchronized void getStatus(String clientId) {
        TokenBucket bucket = clients.get(clientId);
        if (bucket == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.println("Remaining tokens: " +
                bucket.getRemainingTokens());
    }
    
    public static void main(String[] args) {
        RateLimiterSystem limiter = new RateLimiterSystem();
        String client = "abc123";
        for (int i = 0; i < 5; i++) {
            System.out.println(limiter.checkRateLimit(client));
        }
        limiter.getStatus(client);
    }
}