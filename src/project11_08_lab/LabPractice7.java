package project11_08_lab;
import java.util.Scanner;

public class LabPractice7 {
    public static int[] findTrimIndexes(String text) {
        int start = 0, end = text.length() - 1;
        while (start <= end && text.charAt(start) == ' ') start++;
        while (end >= start && text.charAt(end) == ' ') end--;
        return new int[]{start, end};
    }

    public static String substringUsingCharAt(String text, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) sb.append(text.charAt(i));
        return sb.toString();
    }

    public static boolean compareStrings(String a, String b) {
        if (a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text with spaces: ");
        String text = sc.nextLine();
        int[] idx = findTrimIndexes(text);
        String trimmedCustom = substringUsingCharAt(text, idx[0], idx[1]);
        String trimmedBuiltIn = text.trim();
        System.out.println("Custom trim: '" + trimmedCustom + "'");
        System.out.println("Built-in trim: '" + trimmedBuiltIn + "'");
        System.out.println("Are equal? " + compareStrings(trimmedCustom, trimmedBuiltIn));
        sc.close();
    }
}
