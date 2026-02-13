package project07_08;

public class StringManipulation{
    public static void main(String[] args) {
// 1. String literal
// 2. new String() constructor
// 3. Character array
// Print the results and explain the difference
// Programming Quote:
// "Code is poetry" - Unknown
// Path: C:\Java\Projects

        /*
            STACK HEAP

         */
        String s1 = "Hello World";
        String s2 = new String("Hello World");
        String s3 = new String(new char[]{'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'});
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s2 == s3: " + (s2 == s3));
        System.out.println("equals: " + s1.equals(s2));
        String quote = "Programming Quote:\n\"Code is poetry\" - Unknown\n";
        String path = "Path: C:\\Java\\Projects";
        System.out.println(quote);
        System.out.println(path);
    }
}
