package dev;

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    Map<String, Integer> freqMap = new HashMap<>();
}

class AutocompleteSystem {
    private TrieNode root = new TrieNode();
    private Map<String, Integer> globalFreq = new HashMap<>();

    public void addQuery(String query, int freq) {
        globalFreq.put(query, globalFreq.getOrDefault(query, 0) + freq);
        TrieNode node = root;

        for (char c : query.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.freqMap.put(query, globalFreq.get(query));
        }
    }

    public List<String> search(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return new ArrayList<>();
            node = node.children.get(c);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());

        for (var e : node.freqMap.entrySet()) {
            pq.offer(e);
            if (pq.size() > 10) pq.poll();
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) res.add(pq.poll().getKey());
        Collections.reverse(res);
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        AutocompleteSystem ac = new AutocompleteSystem();
        ac.addQuery("java tutorial", 100);
        ac.addQuery("javascript", 90);
        ac.addQuery("java download", 80);

        System.out.println(ac.search("jav"));
    }
}
