import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MultiLevelCacheSystem {
    private final int L1_CAPACITY = 3;
    private final int L2_CAPACITY = 5;
    private final LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(16, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, String> e) {
                    return size() > L1_CAPACITY;
                }
            };
    private final LinkedHashMap<String, String> L2 =
            new LinkedHashMap<>(16, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<String, String> e) {
                    return size() > L2_CAPACITY;
                }
            };
    private final Map<String, Integer> accessCount = new HashMap<>();
    private final Map<String, String> database = new HashMap<>();

    private int L1Hits = 0;
    private int L2Hits = 0;
    private int L3Hits = 0;

    public MultiLevelCacheSystem() {
        database.put("video1", "Video Data 1");
        database.put("video2", "Video Data 2");
        database.put("video3", "Video Data 3");
        database.put("video4", "Video Data 4");
        database.put("video5", "Video Data 5");
        database.put("video6", "Video Data 6");
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.getVideo("video1"); // L3
        cache.getVideo("video1"); // L2
        cache.getVideo("video1"); // L1
        cache.getVideo("video2"); // L3
        cache.getVideo("video3"); // L3
        cache.getVideo("video4"); // L3
        cache.getVideo("video5"); // L3
        cache.getVideo("video1"); // L1
        cache.printStats();
    }

    public String getVideo(String videoId) {
        if (L1.containsKey(videoId)) {
            L1Hits++;
            System.out.println("L1 HIT");
            incrementAccess(videoId);
            return L1.get(videoId);
        }
        if (L2.containsKey(videoId)) {
            L2Hits++;
            System.out.println("L2 HIT → Promoted to L1");

            String data = L2.get(videoId);
            L1.put(videoId, data);

            incrementAccess(videoId);
            return data;
        }
        if (database.containsKey(videoId)) {
            L3Hits++;
            System.out.println("L3 HIT → Added to L2");

            String data = database.get(videoId);
            L2.put(videoId, data);

            incrementAccess(videoId);
            return data;
        }

        return "Video Not Found";
    }

    private void incrementAccess(String videoId) {
        int count = accessCount.getOrDefault(videoId, 0) + 1;
        accessCount.put(videoId, count);
        if (count >= 3 && L2.containsKey(videoId)) {
            System.out.println("Promoted to L1 (popular)");
            L1.put(videoId, L2.get(videoId));
        }
    }

    public void printStats() {
        int total = L1Hits + L2Hits + L3Hits;

        System.out.println("\n--- Cache Stats ---");
        System.out.println("L1 Hits: " + L1Hits);
        System.out.println("L2 Hits: " + L2Hits);
        System.out.println("L3 Hits: " + L3Hits);

        if (total > 0) {
            System.out.println("Overall Hit Rate: "
                    + String.format("%.2f",
                    ((L1Hits + L2Hits) * 100.0) / total) + "%");
        }
    }
}