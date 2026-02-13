package project_23_08_HW;

import java.util.*;

public class PasswordAnalyzer {
    public static int[] analyze(String pwd) {
        int up = 0, low = 0, dig = 0, spec = 0;
        for (char c : pwd.toCharArray()) {
            if (c >= 'A' && c <= 'Z') up++;
            else if (c >= 'a' && c <= 'z') low++;
            else if (c >= '0' && c <= '9') dig++;
            else if (c >= 33 && c <= 126) spec++;
        }
        return new int[]{up, low, dig, spec};
    }

    public static int score(String pwd) {
        int[] arr = analyze(pwd);
        int lenPoints = Math.max(0, (pwd.length() - 8) * 2);
        int variety = 0;
        for (int x : arr) if (x > 0) variety += 10;
        int deduct = (pwd.contains("123") || pwd.contains("abc") || pwd.toLowerCase().contains("qwerty")) ? 15 : 0;
        return lenPoints + variety - deduct;
    }

    public static String level(int score) {
        if (score <= 20) return "Weak";
        if (score <= 50) return "Medium";
        return "Strong";
    }

    public static String generate(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()_+[]{}";
        String all = upper + lower + digits + special;

        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(r.nextInt(upper.length())));
        sb.append(lower.charAt(r.nextInt(lower.length())));
        sb.append(digits.charAt(r.nextInt(digits.length())));
        sb.append(special.charAt(r.nextInt(special.length())));
        while (sb.length() < length) sb.append(all.charAt(r.nextInt(all.length())));

        List<Character> chars = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);
        StringBuilder shuffled = new StringBuilder();
        for (char c : chars) shuffled.append(c);
        return shuffled.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter passwords (space separated):");
        String[] pwds = sc.nextLine().split(" ");

        System.out.printf("%-15s %-8s %-8s %-8s %-8s %-8s %-8s %-10s\n",
                "Password","Len","Upper","Lower","Digits","Spec","Score","Strength");
        for (String p : pwds) {
            int[] a = analyze(p);
            int s = score(p);
            System.out.printf("%-15s %-8d %-8d %-8d %-8d %-8d %-8d %-10s\n",
                    p, p.length(), a[0], a[1], a[2], a[3], s, level(s));
        }

        System.out.println("\nGenerated Strong Password: " + generate(12));
    }
}
