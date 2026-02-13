package project11_08_lab;

import java.util.Scanner;

public class LabPractice1 {
    public static int getLengthWithoutLengthMethod(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // End of string reached
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.next();
        int lengthWithout = getLengthWithoutLengthMethod(input);
        int lengthWith = input.length();
        System.out.println("Length without length(): " + lengthWithout);
        System.out.println("Length with length(): " + lengthWith);
        sc.close();
    }
}
