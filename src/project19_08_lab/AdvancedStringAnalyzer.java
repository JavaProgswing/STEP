package project19_08_lab;
import java.util.*;

public class AdvancedStringAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first string: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter second string: ");
        String s2 = scanner.nextLine();

        performAllComparisons(s1, s2);
        System.out.println("Similarity: " + calculateSimilarity(s1, s2) + "%");

        scanner.close();
    }

    public static double calculateSimilarity(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (s1.charAt(i-1) == s2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else dp[i][j] = 1 + Math.min(dp[i-1][j-1],
                            Math.min(dp[i-1][j], dp[i][j-1]));
            }
        }
        int dist = dp[len1][len2];
        int maxLen = Math.max(len1, len2);
        return 100.0 * (1 - (double)dist / maxLen);
    }

    public static void performAllComparisons(String str1, String str2) {
        System.out.println("== Reference Equality (==): " + (str1 == str2));
        System.out.println("== Content Equality (equals): " + str1.equals(str2));
        System.out.println("== Case-insensitive Equality: " + str1.equalsIgnoreCase(str2));
        System.out.println("== Lexicographic compareTo: " + str1.compareTo(str2));
        System.out.println("== Lexicographic IgnoreCase: " + str1.compareToIgnoreCase(str2));
    }
}
