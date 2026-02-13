package project23_08_lab;

import java.util.*;

public class StringPerformance {
    public static long testStringConcat(int iterations) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) s += "x";
        return System.currentTimeMillis() - start;
    }

    public static long testStringBuilder(int iterations) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) sb.append("x");
        return System.currentTimeMillis() - start;
    }

    public static long testStringBuffer(int iterations) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) sb.append("x");
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int n = sc.nextInt();

        System.out.println("Method\t\tTime(ms)");
        System.out.println("String\t\t" + testStringConcat(n));
        System.out.println("StringBuilder\t" + testStringBuilder(n));
        System.out.println("StringBuffer\t" + testStringBuffer(n));
    }
}
