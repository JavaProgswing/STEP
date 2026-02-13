package project23_08_lab;

import java.util.*;

public class EmailAnalyser {
    public static boolean isValid(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        int dot = email.lastIndexOf('.');
        return at > 0 && at == lastAt && dot > at + 1 && dot < email.length() - 1;
    }

    public static void analyze(String email) {
        if (!isValid(email)) {
            System.out.println(email + " → Invalid");
            return;
        }
        String username = email.substring(0, email.indexOf('@'));
        String domain = email.substring(email.indexOf('@') + 1);
        String domainName = domain.substring(0, domain.lastIndexOf('.'));
        String ext = domain.substring(domain.lastIndexOf('.') + 1);

        System.out.printf("%-25s %-10s %-15s %-10s %-8s %s\n",
                email, username, domain, domainName, ext, "Valid");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter emails separated by space:");
        String[] emails = sc.nextLine().split(" ");

        System.out.printf("%-25s %-10s %-15s %-10s %-8s %s\n",
                "Email", "Username", "Domain", "DomainName", "Ext", "Status");
        for (String e : emails) analyze(e);
    }
}
