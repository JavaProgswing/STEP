package dev;

import java.util.*;

class PlagiarismDetector {

    private final int N = 5; // n-gram size

    // ngram -> documents containing it
    private final Map<String, Set<String>> index = new HashMap<>();

    // doc -> its ngrams
    private final Map<String, List<String>> docNgrams = new HashMap<>();

    public void addDocument(String docId, String text) {
        List<String> grams = extractNgrams(text);
        docNgrams.put(docId, grams);

        for (String g : grams) {
            index.computeIfAbsent(g, k -> new HashSet<>()).add(docId);
        }
    }

    public void analyze(String docId, String text) {
        List<String> grams = extractNgrams(text);

        Map<String, Integer> matchCount = new HashMap<>();

        for (String g : grams) {
            if (index.containsKey(g)) {
                for (String other : index.get(g)) {
                    matchCount.merge(other, 1, Integer::sum);
                }
            }
        }

        System.out.println("Extracted " + grams.size() + " n-grams");

        for (var e : matchCount.entrySet()) {
            int matches = e.getValue();
            double sim = matches * 100.0 / grams.size();

            System.out.printf("Match with %s → %d n-grams → %.2f%% similarity%n", e.getKey(), matches, sim);
        }
    }

    private List<String> extractNgrams(String text) {
        String[] words = text.toLowerCase().split("\\W+");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i + N <= words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(words[i + j]).append(" ");
            }
            grams.add(sb.toString().trim());
        }

        return grams;
    }
}

public class Main {
    public static void main(String[] args) {

        PlagiarismDetector p = new PlagiarismDetector();

        p.addDocument("essay_089", "Machine learning is a method of data analysis that automates analytical model building");

        p.addDocument("essay_092", "Machine learning is a method of data analysis that automates analytical model building using algorithms");

        String newDoc = "Machine learning is a method of data analysis that automates analytical model building";

        p.analyze("essay_new", newDoc);
    }
}
