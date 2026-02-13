package project11_08_lab;
import java.util.Scanner;

public class LabPractice3 {
    public static int getLength(String str) {
        int count = 0;
        try {
            while (true) { str.charAt(count); count++; }
        } catch (StringIndexOutOfBoundsException e) {}
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        String[] words = splitWords(text);
        String[][] table = wordsWithLengths(words);
        System.out.println("Word\tLength");
        for (String[] row : table) {
            System.out.println(row[0] + "\t" + Integer.parseInt(row[1]));
        }
        sc.close();
    }
}
