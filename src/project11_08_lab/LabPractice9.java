package project11_08_lab;
import java.util.Random;
import java.util.Scanner;

public class LabPractice9 {
    public static String getComputerChoice() {
        String[] choices = {"rock", "paper", "scissors"};
        Random r = new Random();
        return choices[r.nextInt(3)];
    }

    public static int findWinner(String user, String comp) {
        if (user.equals(comp)) return 0;
        if (user.equals("rock") && comp.equals("scissors")) return 1;
        if (user.equals("scissors") && comp.equals("paper")) return 1;
        if (user.equals("paper") && comp.equals("rock")) return 1;
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of games: ");
        int n = sc.nextInt();
        int userWins = 0, compWins = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter rock/paper/scissors: ");
            String user = sc.next().toLowerCase();
            String comp = getComputerChoice();
            int res = findWinner(user, comp);
            if (res == 1) userWins++;
            else if (res == -1) compWins++;
            System.out.println("Computer chose: " + comp);
        }
        System.out.println("User wins: " + userWins);
        System.out.println("Computer wins: " + compWins);
        System.out.printf("User win %%: %.2f%%\n", (userWins*100.0)/n);
        System.out.printf("Computer win %%: %.2f%%\n", (compWins*100.0)/n);
        sc.close();
    }
}
