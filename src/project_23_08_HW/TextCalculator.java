package project_23_08_HW;

import java.util.*;

public class TextCalculator {
    public static boolean valid(String expr) {
        return expr.matches("[0-9+\\-*/() ]+");
    }

    public static int evalSimple(String expr) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int num = 0;
        boolean building = false;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                building = true;
            } else {
                if (building) {
                    nums.push(num);
                    num = 0;
                    building = false;
                }
                if (c == '+' || c == '-') {
                    while (!ops.isEmpty()) {
                        char op = ops.pop();
                        int b = nums.pop(), a = nums.pop();
                        nums.push(op == '+' ? a + b : a - b);
                    }
                    ops.push(c);
                } else if (c == '*' || c == '/') {
                    int j = i + 1, val = 0;
                    while (j < expr.length() && Character.isDigit(expr.charAt(j))) {
                        val = val * 10 + (expr.charAt(j) - '0');
                        j++;
                    }
                    i = j - 1;
                    int a = nums.pop();
                    nums.push(c == '*' ? a * val : a / val);
                }
            }
        }
        if (building) nums.push(num);
        while (!ops.isEmpty()) {
            char op = ops.pop();
            int b = nums.pop(), a = nums.pop();
            nums.push(op == '+' ? a + b : a - b);
        }
        return nums.pop();
    }

    public static int eval(String expr) {
        while (expr.contains("(")) {
            int close = expr.indexOf(")");
            int open = expr.lastIndexOf("(", close);
            int val = evalSimple(expr.substring(open + 1, close));
            expr = expr.substring(0, open) + val + expr.substring(close + 1);
        }
        return evalSimple(expr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter expression:");
        String expr = sc.nextLine();
        if (!valid(expr)) {
            System.out.println("Invalid expression!");
            return;
        }
        int res = eval(expr.replace(" ", ""));
        System.out.println("Result: " + res);
    }
}

