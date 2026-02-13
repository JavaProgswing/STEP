package dev;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Event {
    String url;
    String userId;
    String source;

    Event(String u, String user, String s) {
        url = u;
        userId = user;
        source = s;
    }
}

class Analytics {

    private final Map<String, Integer> pageViews = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> uniqueVisitors = new ConcurrentHashMap<>();
    private final Map<String, Integer> sourceCount = new ConcurrentHashMap<>();

    public void process(Event e) {

        pageViews.merge(e.url, 1, Integer::sum);

        uniqueVisitors.computeIfAbsent(e.url, k -> ConcurrentHashMap.newKeySet()).add(e.userId);

        sourceCount.merge(e.source, 1, Integer::sum);
    }

    public void dashboard() {

        System.out.println("\n=== DASHBOARD ===");

        // top pages
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(pageViews.entrySet());

        System.out.println("Top Pages:");
        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            var e = pq.poll();
            int uniq = uniqueVisitors.getOrDefault(e.getKey(), Set.of()).size();
            System.out.println((i + 1) + ". " + e.getKey() + " → " + e.getValue() + " views (" + uniq + " unique)");
        }

        int total = sourceCount.values().stream().mapToInt(i -> i).sum();

        System.out.println("\nSources:");
        for (var e : sourceCount.entrySet()) {
            double pct = total == 0 ? 0 : (e.getValue() * 100.0 / total);
            System.out.printf("%s: %.1f%%%n", e.getKey(), pct);
        }
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        Analytics a = new Analytics();

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

        exec.scheduleAtFixedRate(a::dashboard, 5, 5, TimeUnit.SECONDS);

        // simulate traffic
        String[] urls = {"/news", "/sports", "/tech"};
        String[] src = {"google", "direct", "facebook"};

        Random r = new Random();

        while (true) {
            Event e = new Event(urls[r.nextInt(urls.length)], "user" + r.nextInt(1000), src[r.nextInt(src.length)]);
            a.process(e);
            Thread.sleep(50);
        }
    }
}
