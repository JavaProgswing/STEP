package project11_08_practise;
import java.util.Scanner;

public class PracticeProblem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inputs
        System.out.print("Enter your full name (first and last): ");
        String fullName = sc.nextLine();
        System.out.print("Enter your favorite programming language: ");
        String favLang = sc.nextLine();
        System.out.print("Enter a sentence about your programming experience: ");
        String sentence = sc.nextLine();

        // Process: Extract first and last name
        String firstName = fullName.substring(0, fullName.indexOf(" "));
        String lastName = fullName.substring(fullName.indexOf(" ") + 1);

        // Count characters in sentence excluding spaces
        int charCount = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) != ' ') charCount++;
        }

        // Convert language to uppercase
        String favLangUpper = favLang.toUpperCase();

        // Output summary
        System.out.println("\n--- Summary ---");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Sentence character count (no spaces): " + charCount);
        System.out.println("Favorite Language (uppercase): " + favLangUpper);

        sc.close();
    }
}
