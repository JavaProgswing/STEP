package project11_08_practise;
import java.util.*;

public class PracticeProblem4 {
    public static String cleanInput(String input) {
        // Remove extra spaces and convert to proper case
        input = input.trim().replaceAll("\\s+", " ");
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void analyzeText(String text) {
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        int charCount = text.replace(" ", "").length();
        int sentenceCount = text.split("[.!?]").length;

        String longestWord = "";
        Map<Character, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            if (word.length() > longestWord.length()) longestWord = word;
            for (char c : word.toCharArray()) {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }
        char mostCommon = ' ';
        int maxFreq = 0;
        for (var e : freqMap.entrySet()) {
            if (e.getValue() > maxFreq) {
                maxFreq = e.getValue();
                mostCommon = e.getKey();
            }
        }

        System.out.println("Word count: " + wordCount);
        System.out.println("Sentence count: " + sentenceCount);
        System.out.println("Character count (no spaces): " + charCount);
        System.out.println("Longest word: " + longestWord);
        System.out.println("Most common character: " + mostCommon);
    }

    public static String[] getWordsSorted(String text) {
        String[] words = text.replaceAll("[^a-zA-Z ]", "").split("\\s+");
        Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
        return words;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph: ");
        String paragraph = sc.nextLine();

        String cleaned = cleanInput(paragraph);
        analyzeText(cleaned);

        String[] sortedWords = getWordsSorted(cleaned);
        System.out.println("Words in alphabetical order:");
        for (String w : sortedWords) System.out.println(w);

        System.out.print("Search for a word: ");
        String search = sc.nextLine().trim();
        boolean found = false;
        for (String w : sortedWords) {
            if (w.equalsIgnoreCase(search)) { found = true; break; }
        }
        System.out.println(found ? "Word found!" : "Word not found.");

        sc.close();
    }
}
