package project11_08_lab;
import java.util.Scanner;

public class LabPractice4 {
    public static int getLength(String str) {
        int count = 0;
        try { while (true) { str.charAt(count); count++; } }
        catch (StringIndexOutOfBoundsException e) {}
        return count;
    }

    public static String[] splitWords(String text) {
        int count = 1;
        for (int i = 0; i < getLength(text); i++) if (text.charAt(i) == ' ') count++;
        String[] words = new String[count];
        StringBuilder sb = new StringBuilder();
        int wi = 0;
        for (int i = 0; i < getLength(text); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                words[wi++] = sb.toString();
                sb.setLength(0);
            } else sb.append(c);
        }
        words[wi] = sb.toString();
        return words;
    }

    public static String[][] wordsWithLengths(String[] words) {
        String[][] out = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            out[i][0] = words[i];
            out[i][1] = String.valueOf(getLength(words[i]));
        }
        return out;
    }

    public static int[] findShortestAndLongest(String[][] table) {
        int shortestIndex = 0, longestIndex = 0;
        for (int i = 1; i < table.length; i++) {
            int len = Integer.parseInt(table[i][1]);
            if (len < Integer.parseInt(table[shortestIndex][1])) shortestIndex = i;
            if (len > Integer.parseInt(table[longestIndex][1])) longestIndex = i;
        }
        return new int[]{shortestIndex, longestIndex};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        String[] words = splitWords(text);
        String[][] table = wordsWithLengths(words);
        int[] idx = findShortestAndLongest(table);
        System.out.println("Shortest word: " + table[idx[0]][0]);
        System.out.println("Longest word: " + table[idx[1]][0]);
        sc.close();
    }
}
