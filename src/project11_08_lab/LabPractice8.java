package project11_08_lab;
import java.util.Random;

public class LabPractice8 {
    public static int[] generateRandomAges(int n) {
        Random r = new Random();
        int[] ages = new int[n];
        for (int i = 0; i < n; i++) ages[i] = r.nextInt(90) + 1;
        return ages;
    }

    public static String[][] checkVotingEligibility(int[] ages) {
        String[][] table = new String[ages.length][2];
        for (int i = 0; i < ages.length; i++) {
            table[i][0] = String.valueOf(ages[i]);
            if (ages[i] < 0) table[i][1] = "false";
            else table[i][1] = ages[i] >= 18 ? "true" : "false";
        }
        return table;
    }

    public static void displayTable(String[][] table) {
        System.out.println("Age\tCanVote");
        for (String[] row : table) {
            System.out.println(row[0] + "\t" + row[1]);
        }
    }

    public static void main(String[] args) {
        int[] ages = generateRandomAges(10);
        String[][] table = checkVotingEligibility(ages);
        displayTable(table);
    }
}
