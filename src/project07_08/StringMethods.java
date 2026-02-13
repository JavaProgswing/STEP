package project07_08;

import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
// 1. Extract first and last name separately
// 2. Count total characters in the sentence (excluding spaces)
// 3. Convert programming language to uppercase
// 4. Display a formatted summary
        System.out.print("Enter your full name (first and last): ");
        String fullName = scanner.nextLine();
        String[] parts = fullName.split(" ");
        String firstName = parts[0];
        String lastName = parts[1];
        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine();
        System.out.print("Enter a sentence about your programming experience: ");
        String experience = scanner.nextLine().replace(" ", "");
        System.out.println("\nSummary:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Total characters in your experience: " + experience);
        System.out.println("Favorite Programming Language: " + language.toUpperCase());
        scanner.close();
    }
}

