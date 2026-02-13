package project23_08_lab;

import java.util.*;

public class CaseConversion {
    public static char toUpper(char c) {
        if (c >= 'a' && c <= 'z') return (char)(c - 32);
        return c;
    }

    public static char toLower(char c) {
        if (c >= 'A' && c <= 'Z') return (char)(c + 32);
        return c;
    }

    public static String toUpperCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(toUpper(c));
        return sb.toString();
    }

    public static String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(toLower(c));
        return sb.toString();
    }

    public static String toTitleCase(String s) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                newWord = true;
                sb.append(c);
            } else {
                sb.append(newWord ? toUpper(c) : toLower(c));
                newWord = false;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String input = sc.nextLine();

        System.out.println("ASCII Uppercase: " + toUpperCase(input));
        System.out.println("ASCII Lowercase: " + toLowerCase(input));
        System.out.println("ASCII Titlecase: " + toTitleCase(input));

        System.out.println("Built-in Uppercase: " + input.toUpperCase());
        System.out.println("Built-in Lowercase: " + input.toLowerCase());
    }
}
