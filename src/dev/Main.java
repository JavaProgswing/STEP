package dev;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

class MultiLevelCache {
    LRUCache<String, String> L1 = new LRUCache<>(3);
    Map<String, String> L2 = new HashMap<>();
    Map<String, String> DB = new HashMap<>();

    public String get(String key) {
        if (L1.containsKey(key)) {
            System.out.println("L1 hit");
            return L1.get(key);
        }

        if (L2.containsKey(key)) {
            System.out.println("L2 hit → promote to L1");
            String v = L2.get(key);
            L1.put(key, v);
            return v;
        }

        if (DB.containsKey(key)) {
            System.out.println("DB hit → add to L2");
            String v = DB.get(key);
            L2.put(key, v);
            return v;
        }

        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        MultiLevelCache cache = new MultiLevelCache();
        cache.DB.put("video1", "data1");

        cache.get("video1");
        cache.get("video1");
    }
}
