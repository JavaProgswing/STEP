import java.util.*;

public class RealTimeAnalytics {
    private final Map<String, Integer> pageViews = new HashMap<>();
    private final Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    private final Map<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {
        pageViews.merge(url, 1, Integer::sum);
        uniqueVisitors
                .computeIfAbsent(url, k -> new HashSet<>())
                .add(userId);
        trafficSources.merge(source, 1, Integer::sum);
    }

    public List<String> getTopPages(int n) {
        PriorityQueue<Map.Entry<String, Integer>> minHeap =
                new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Map.Entry<String, Integer> entry : pageViews.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > n) {
                minHeap.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Map.Entry<String, Integer> entry = minHeap.poll();
            String page = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(page).size();
            result.add(page + " - " + views + " views (" +
                    unique + " unique)");
        }

        Collections.reverse(result);
        return result;
    }

    public void printTrafficSources() {
        int total = trafficSources.values()
                .stream().mapToInt(i -> i).sum();

        System.out.println("\nTraffic Sources:");

        for (Map.Entry<String, Integer> entry : trafficSources.entrySet()) {
            double percent = (entry.getValue() * 100.0) / total;
            System.out.println(entry.getKey() + ": " +
                    String.format("%.2f", percent) + "%");
        }
    }

    public void getDashboard() {
        System.out.println("\n---- Real-Time Dashboard ----");

        List<String> topPages = getTopPages(10);
        System.out.println("\nTop Pages:");
        for (int i = 0; i < topPages.size(); i++) {
            System.out.println((i + 1) + ". " + topPages.get(i));
        }

        printTrafficSources();
    }

    public static void main(String[] args) {
        RealTimeAnalytics analytics = new RealTimeAnalytics();

        analytics.processEvent("/article/breaking-news", "user1", "google");
        analytics.processEvent("/article/breaking-news", "user2", "facebook");
        analytics.processEvent("/sports/championship", "user3", "google");
        analytics.processEvent("/sports/championship", "user4", "direct");
        analytics.processEvent("/sports/championship", "user3", "google");
        analytics.processEvent("/article/breaking-news", "user1", "google");
        analytics.getDashboard();
    }
}