import java.util.HashSet;
import java.util.Set;

public class TrainConsistMgmnt {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println(" UC3 - Track Unique Bogie IDs ");
        System.out.println("========================================\n");
        // HashSet automatically removes duplicates
        Set<String> bogies = new HashSet<>();
        bogies.add("BG101");
        bogies.add("BG102");
        bogies.add("BG103");
        bogies.add("BG104");
        bogies.add("BG101");
        bogies.add("BG102");
        System.out.println("Bogie IDs added (including duplicates):");
        System.out.println("[BG101, BG102, BG103, BG104, BG101, BG102]");

        System.out.println("\nUnique Bogie IDs in Train:");
        System.out.println(bogies);

        System.out.println("\nTotal Unique Bogies: " + bogies.size());

        System.out.println("\nNote: Duplicate IDs are automatically ignored by HashSet.");

        System.out.println("\nSystem ready for next operation...");
    }
}