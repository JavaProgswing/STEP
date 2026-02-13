package project_23_08_HW;

import java.util.*;

public class SpellChecker {
    public static List<String> splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || !Character.isLetter(text.charAt(i))) {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        return words;
    }

    public static int stringDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[a.length()][b.length()];
    }

    public static String suggest(String word, String[] dict) {
        String best = word;
        int minDist = Integer.MAX_VALUE;
        for (String d : dict) {
            int dist = stringDistance(word.toLowerCase(), d.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                best = d;
            }
        }
        return (minDist <= 2) ? best : word;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dict = {"java", "python", "program", "hello", "world", "computer", "science"};
        System.out.println("Enter a sentence:");
        String sentence = sc.nextLine();

        List<String> words = splitWords(sentence);

        System.out.printf("%-15s %-15s %-10s %-10s\n", "Word", "Suggestion", "Distance", "Status");
        for (String w : words) {
            String s = suggest(w, dict);
            int d = stringDistance(w.toLowerCase(), s.toLowerCase());
            System.out.printf("%-15s %-15s %-10d %-10s\n", w, s, d, (d == 0 ? "Correct" : "Misspelled"));
        }
    }
}
