package project07_08;

import java.util.Scanner;

public class TextProcessor {
    public static String cleanInput(String input) {
// Remove extra spaces, convert to proper case
// Return cleaned string
        // Convert to proper case (capitalize first letter of each word)

        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void analyzeText(String text) {
// Count: words, sentences, characters
// Find: longest word, most common character
// Display statistics
        String[] words = text.split(" ");
        int wordCount = words.length;
        int charCount = text.replace(" ", "").length();
        String longestWord = "";
        int[] charFrequency = new int[256]; // ASCII character set

        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
            for (char c : word.toCharArray()) {
                charFrequency[c]++;
            }
        }

        char mostCommonChar = ' ';
        int maxFrequency = 0;
        for (int i = 0; i < charFrequency.length; i++) {
            if (charFrequency[i] > maxFrequency) {
                maxFrequency = charFrequency[i];
                mostCommonChar = (char) i;
            }
        }

        System.out.println("Word Count: " + wordCount);
        System.out.println("Character Count " + charCount);
        System.out.println("Longest Word: " + longestWord);
        System.out.println("Most Common Character: '" + mostCommonChar + "' with frequency: " + maxFrequency);
    }

    // TODO: Method to create word array and sort alphabetically
    public static String[] getWordsSorted(String text) {
// Split text into words, remove punctuation, sort
// Return sorted array
        String[] words = text.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replace("[^a-zA-Z]", "").toLowerCase(); // Remove punctuation and convert to lowercase
        }
        java.util.Arrays.sort(words);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// 1. Asks user for a paragraph of text
// 2. Cleans and validates the input
// 3. Analyzes the text (word count, character count, etc.)
// 4. Shows the words in alphabetical order
// 5. Allows user to search for specific words
        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph of text: ");
        String inputText = scanner.nextLine();
        String cleanedText = cleanInput(inputText);
        System.out.println("Cleaned Text: " + cleanedText);
        analyzeText(cleanedText);
        String[] sortedWords = getWordsSorted(cleanedText);
        System.out.println("Words in Alphabetical Order:");
        for (String word : sortedWords) {
            System.out.print(word + " ");
        }
        System.out.println();
        System.out.print("Search for a word:");
        String searchWord = scanner.nextLine();
        boolean found = false;
        for (String word : sortedWords) {
            if (word.equalsIgnoreCase(searchWord)) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("The word '" + searchWord + "' was found in the text.");
        } else {
            System.out.println("The word '" + searchWord + "' was not found in the text.");
        }
        scanner.close();
    }
}
