import java.util.*;

public class AutocompleteSystem {
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        Set<String> queries = new HashSet<>();
    }
    private final TrieNode root = new TrieNode();
    private final Map<String, Integer> frequencyMap = new HashMap<>();

    public void updateFrequency(String query) {
        frequencyMap.merge(query, 1, Integer::sum);

        TrieNode node = root;
        for (char ch : query.toCharArray()) {
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
            node.queries.add(query);
        }
    }
    public List<String> search(String prefix, int k) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) {
                return Collections.emptyList();
            }
        }

        PriorityQueue<String> minHeap =
                new PriorityQueue<>(
                        (a, b) -> frequencyMap.get(a) - frequencyMap.get(b)
                );
        for (String query : node.queries) {
            minHeap.offer(query);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        AutocompleteSystem system = new AutocompleteSystem();
        system.updateFrequency("java tutorial");
        system.updateFrequency("javascript");
        system.updateFrequency("java download");
        system.updateFrequency("java tutorial");
        system.updateFrequency("java 21 features");
        system.updateFrequency("java tutorial");

        List<String> suggestions = system.search("jav", 3);
        System.out.println("Suggestions for 'jav':");
        for (String s : suggestions) {
            System.out.println(s + " (" +
                    system.frequencyMap.get(s) + ")");
        }
    }
}