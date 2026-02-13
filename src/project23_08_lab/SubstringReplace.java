package project23_08_lab;
import java.util.*;

public class SubstringReplace {
    public static int[] findOccurrences(String text, String find) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(find);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(find, index + find.length());
        }
        return positions.stream().mapToInt(i -> i).toArray();
    }

    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i <= text.length() - find.length() &&
                    text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);
                i += find.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareWithBuiltIn(String original, String find, String replace) {
        return original.replace(find, replace).equals(manualReplace(original, find, replace));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter main text:");
        String text = sc.nextLine();
        System.out.println("Enter substring to find:");
        String find = sc.nextLine();
        System.out.println("Enter substring to replace with:");
        String replace = sc.nextLine();

        String manual = manualReplace(text, find, replace);
        String builtin = text.replace(find, replace);

        System.out.println("Manual Result: " + manual);
        System.out.println("Built-in Result: " + builtin);
        System.out.println("Are they same? " + compareWithBuiltIn(text, find, replace));
    }
}
