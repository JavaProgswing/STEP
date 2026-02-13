package project19_08_lab;
import java.util.*;

public class StringUtilityApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== STRING UTILITY APP ===");
        System.out.println("1. Text Analysis");
        System.out.println("2. Transformations");
        System.out.println("3. ASCII Operations");
        System.out.println("4. Performance Test");
        System.out.println("5. Comparison Analysis");

        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice) {
            case 1 -> performTextAnalysis("Java is fun and powerful!");
            case 2 -> System.out.println(performTransformations(" hello world ", new String[]{"trim","upper"}));
            case 3 -> performASCIIOperations("Java123!");
            case 4 -> performPerformanceTest(1000);
            case 5 -> performComparisonAnalysis(new String[]{"Java","java"});
        }

        sc.close();
    }

    public static void performTextAnalysis(String text) {
        System.out.println("Length: " + text.length());
        System.out.println("Word count: " + text.split("\\s+").length);
    }

    public static String performTransformations(String text, String[] ops) {
        StringBuilder sb = new StringBuilder(text);
        for (String op : ops) {
            switch(op) {
                case "trim" -> text = text.trim();
                case "upper" -> text = text.toUpperCase();
                case "lower" -> text = text.toLowerCase();
            }
        }
        return text;
    }

    public static void performASCIIOperations(String text) {
        for (char c : text.toCharArray()) {
            System.out.println(c + " -> " + (int)c);
        }
    }

    public static void performPerformanceTest(int n) {
        long start = System.nanoTime();
        StringPerformanceComparison.concatenateWithString(n);
        long end = System.nanoTime();
        System.out.println("String time: " + (end - start));
    }

    public static void performComparisonAnalysis(String[] arr) {
        System.out.println("Equals: " + arr[0].equals(arr[1]));
        System.out.println("Ignore case: " + arr[0].equalsIgnoreCase(arr[1]));
    }
}

