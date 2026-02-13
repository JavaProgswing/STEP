package project_23_08_HW;

import java.util.*;

public class TextCompression {
    public static char[] uniqueChars(String text) {
        String seen = "";
        for (char c : text.toCharArray()) if (seen.indexOf(c) == -1) seen += c;
        return seen.toCharArray();
    }

    public static int[] countFreq(String text, char[] chars) {
        int[] freq = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            for (char c : text.toCharArray()) if (c == chars[i]) freq[i]++;
        }
        return freq;
    }

    public static String[] buildCodes(char[] chars, int[] freq) {
        String[] codes = new String[chars.length];
        for (int i = 0; i < chars.length; i++) codes[i] = String.valueOf(i);
        return codes;
    }

    public static String compress(String text, char[] chars, String[] codes) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            for (int i = 0; i < chars.length; i++) if (chars[i] == c) sb.append(codes[i]);
        }
        return sb.toString();
    }

    public static String decompress(String comp, char[] chars, String[] codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < comp.length(); i++) {
            int idx = comp.charAt(i) - '0';
            sb.append(chars[idx]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();

        char[] chars = uniqueChars(text);
        int[] freq = countFreq(text, chars);
        String[] codes = buildCodes(chars, freq);

        String comp = compress(text, chars, codes);
        String decomp = decompress(comp, chars, codes);

        System.out.println("Original: " + text);
        System.out.println("Compressed: " + comp);
        System.out.println("Decompressed: " + decomp);
        System.out.println("Efficiency: " + (100.0 * comp.length() / text.length()) + "%");
    }
}
