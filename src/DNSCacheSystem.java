import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class DNSCacheSystem {
    private final int maxSize;
    private final LinkedHashMap<String, DNSEntry> cache;
    private long hits = 0;
    private long misses = 0;

    public DNSCacheSystem(int maxSize) {
        this.maxSize = maxSize;

        this.cache = new LinkedHashMap<String, DNSEntry>(16, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                return size() > DNSCacheSystem.this.maxSize;
            }
        };
    }

    // Demo Main
    public static void main(String[] args) throws InterruptedException {

        DNSCacheSystem cache = new DNSCacheSystem(3);

        System.out.println(cache.resolve("google.com"));
        System.out.println(cache.resolve("google.com")); // HIT

        Thread.sleep(6000); // wait for TTL to expire

        System.out.println(cache.resolve("google.com")); // MISS

        cache.resolve("facebook.com");
        cache.resolve("twitter.com");
        cache.resolve("youtube.com"); // triggers LRU eviction

        cache.printStats();
    }

    // Resolve Domain
    public synchronized String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null) {
            if (!entry.isExpired()) {
                hits++;
                System.out.println("Cache HIT for " + domain);
                return entry.ip;
            } else {
                cache.remove(domain);
                System.out.println("Cache EXPIRED for " + domain);
            }
        }
        misses++;
        System.out.println("Cache MISS for " + domain);

        String ip = queryUpstream(domain);
        cache.put(domain, new DNSEntry(ip, 5)); // TTL = 5 sec
        return ip;
    }

    // Simulate Upstream DNS
    private String queryUpstream(String domain) {
        try {
            Thread.sleep(100); // simulate latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "192.168.1." + new Random().nextInt(255);
    }

    // Stats
    public void printStats() {
        long total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);

        System.out.println("\n---- Cache Stats ----");
        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Hit Rate: " + String.format("%.2f", hitRate) + "%");
    }

    private static class DNSEntry {
        String ip;
        long expiryTime;

        DNSEntry(String ip, long ttlSeconds) {
            this.ip = ip;
            this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }
}