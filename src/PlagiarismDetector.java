import java.util.*;

public class PlagiarismDetector {
    private static final int N = 5;
    private final Map<String, Set<String>> index = new HashMap<>();
    private final Map<String, String> documents = new HashMap<>();

    public void addDocument(String docId, String content) {
        documents.put(docId, content);

        List<String> ngrams = generateNGrams(content);

        for (String gram : ngrams) {
            index.computeIfAbsent(gram, k -> new HashSet<>()).add(docId);
        }
    }

    public void analyzeDocument(String docId) {
        String content = documents.get(docId);
        if (content == null) {
            System.out.println("Document not found.");
            return;
        }

        List<String> ngrams = generateNGrams(content);
        Map<String, Integer> matchCount = new HashMap<>();

        for (String gram : ngrams) {
            Set<String> docs = index.get(gram);
            if (docs != null) {
                for (String otherDoc : docs) {
                    if (!otherDoc.equals(docId)) {
                        matchCount.merge(otherDoc, 1, Integer::sum);
                    }
                }
            }
        }

        System.out.println("Analyzing: " + docId);
        System.out.println("Total n-grams: " + ngrams.size());

        for (Map.Entry<String, Integer> entry : matchCount.entrySet()) {
            double similarity =
                    (entry.getValue() * 100.0) / ngrams.size();

            System.out.println("Matched with: " + entry.getKey());
            System.out.println("Matching n-grams: " + entry.getValue());
            System.out.println("Similarity: " +
                    String.format("%.2f", similarity) + "%");

            if (similarity > 50) {
                System.out.println("⚠ PLAGIARISM DETECTED");
            }

            System.out.println();
        }
    }

    private List<String> generateNGrams(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        List<String> result = new ArrayList<>();

        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < N; j++) {
                sb.append(words[i + j]).append(" ");
            }

            result.add(sb.toString().trim());
        }

        return result;
    }

    public static void main(String[] args) {
        PlagiarismDetector detector = new PlagiarismDetector();

        detector.addDocument("essay_1",
                "data structures and algorithms are important for coding interviews");
        detector.addDocument("essay_2",
                "data structures and algorithms are important for competitive programming");
        detector.addDocument("essay_3",
                "machine learning and artificial intelligence are future technologies");
        detector.analyzeDocument("essay_2");
    }
}
