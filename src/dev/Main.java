package dev;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class DNSEntry {
    String domain;
    String ip;
    long expiry;

    DNSEntry(String d, String ip, long ttlSeconds) {
        this.domain = d;
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    boolean expired() {
        return System.currentTimeMillis() > expiry;
    }
}

class DNSCache {

    private final int MAX = 1000;

    private final LinkedHashMap<String, DNSEntry> cache = new LinkedHashMap<>(16, 0.75f, true) {
        protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> e) {
            return size() > MAX;
        }
    };

    private int hits = 0;
    private int misses = 0;

    public DNSCache() {
        startCleaner();
    }

    // resolve domain
    public synchronized String resolve(String domain) {

        DNSEntry e = cache.get(domain);

        if (e != null && !e.expired()) {
            hits++;
            return "HIT → " + e.ip;
        }

        misses++;

        // simulate upstream DNS
        String ip = queryUpstream(domain);

        cache.put(domain, new DNSEntry(domain, ip, 5));
        return "MISS → " + ip;
    }

    private String queryUpstream(String d) {
        return "172.217." + new Random().nextInt(200) + "." + new Random().nextInt(200);
    }

    // background cleaner
    private void startCleaner() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            synchronized (this) {
                cache.entrySet().removeIf(e -> e.getValue().expired());
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    public void stats() {
        int total = hits + misses;
        double rate = total == 0 ? 0 : (hits * 100.0 / total);
        System.out.println("Hit rate: " + rate + "%");
    }
}

public class Main {
    public static void main(String[] args) throws Exception {

        DNSCache dns = new DNSCache();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("openai.com"));

        Thread.sleep(6000);

        System.out.println(dns.resolve("google.com"));

        dns.stats();
    }
}

