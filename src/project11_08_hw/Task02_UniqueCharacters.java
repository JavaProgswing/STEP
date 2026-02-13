package project11_08_hw;
import java.util.*;

public class Task02_UniqueCharacters {
    public static int getLength(String text) {
        int length = 0;
        for (char c : text.toCharArray()) length++;
        return length;
    }

    public static char[] findUniqueChars(String text) {
        char[] unique = new char[getLength(text)];
        int index = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            boolean found = false;
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == ch) {
                    found = true;
                    break;
                }
            }
            if (!found) unique[index++] = ch;
        }
        return Arrays.copyOf(unique, index);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();
        char[] unique = findUniqueChars(text);
        System.out.println("Unique characters: " + Arrays.toString(unique));
    }
}