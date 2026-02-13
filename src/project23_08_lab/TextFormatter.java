package project23_08_lab;

import java.util.*;

public class TextFormatter {
    public static List<String> splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }

    public static List<String> justify(String text, int width) {
        List<String> words = splitWords(text);
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int len = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && len + 1 + words.get(j).length() <= width) {
                len += 1 + words.get(j).length();
                j++;
            }
            StringBuilder sb = new StringBuilder();
            int gaps = j - i - 1;
            if (j == words.size() || gaps == 0) {
                for (int k = i; k < j; k++) sb.append(words.get(k)).append(" ");
                sb.setLength(sb.length() - 1);
            } else {
                int spaces = (width - (len - gaps)) / gaps;
                int extra = (width - (len - gaps)) % gaps;
                for (int k = i; k < j - 1; k++) {
                    sb.append(words.get(k));
                    for (int s = 0; s < spaces + (k - i < extra ? 1 : 0); s++) sb.append(" ");
                }
                sb.append(words.get(j - 1));
            }
            lines.add(sb.toString());
            i = j;
        }
        return lines;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.print("Enter width: ");
        int w = sc.nextInt();

        List<String> lines = justify(text, w);
        int ln = 1;
        for (String line : lines) {
            System.out.printf("%2d | %-"+w+"s | %d\n", ln++, line, line.length());
        }
    }
}

