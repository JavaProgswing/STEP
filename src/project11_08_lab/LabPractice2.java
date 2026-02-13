package project11_08_lab;

import java.util.Scanner;

public class LabPractice2 {
    public static int getLengthWithoutLengthMethod(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        return count;
    }

    public static String[] splitWithoutSplitMethod(String text) {
        int count = 1;
        for (int i = 0; i < getLengthWithoutLengthMethod(text); i++) {
            if (text.charAt(i) == ' ') count++;
        }
        String[] words = new String[count];
        int wordIndex = 0;
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < getLengthWithoutLengthMethod(text); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                words[wordIndex++] = current.toString();
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        words[wordIndex] = current.toString();
        return words;
    }

    public static boolean compareArrays(String[] a, String[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String text = sc.nextLine();
        String[] customSplit = splitWithoutSplitMethod(text);
        String[] builtInSplit = text.split(" ");
        System.out.println("Custom Split:");
        for (String w : customSplit) System.out.println(w);
        System.out.println("Built-in Split:");
        for (String w : builtInSplit) System.out.println(w);
        System.out.println("Are equal? " + compareArrays(customSplit, builtInSplit));
        sc.close();
    }
}
