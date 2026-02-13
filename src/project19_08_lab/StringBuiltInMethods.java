package project19_08_lab;
public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = "  Java Programming is Fun and Challenging!  ";

        System.out.println("Original length (with spaces): " + sampleText.length());

        String trimmed = sampleText.trim();
        System.out.println("Trimmed length: " + trimmed.length());

        System.out.println("Character at index 5: " + sampleText.charAt(5));

        String sub = sampleText.substring(7, 18);
        System.out.println("Substring: " + sub);

        int funIndex = sampleText.indexOf("Fun");
        System.out.println("Index of 'Fun': " + funIndex);

        System.out.println("Contains 'Java': " + sampleText.contains("Java"));

        System.out.println("Starts with 'Java' (after trim): " + trimmed.startsWith("Java"));

        System.out.println("Ends with '!': " + sampleText.endsWith("!"));

        System.out.println("Uppercase: " + sampleText.toUpperCase());
        System.out.println("Lowercase: " + sampleText.toLowerCase());

        System.out.println("Vowel count: " + countVowels(sampleText));

        System.out.println("Occurrences of 'a': ");
        findAllOccurrences(sampleText, 'a');
    }

    public static int countVowels(String text) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < text.length(); i++) {
            if (vowels.indexOf(text.charAt(i)) != -1) count++;
        }
        return count;
    }

    public static void findAllOccurrences(String text, char target) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.println("Found at index: " + i);
            }
        }
    }
}
