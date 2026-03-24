import java.util.ArrayList;
import java.util.List;

public class TrainConsistMgmnt {

    public static void main(String[] args) {

        // Header
        System.out.println("========================================");
        System.out.println("   === Train Consist Management App ===  ");
        System.out.println("========================================\n");

        // Initialize train consist
        List<String> trainConsist = new ArrayList<>();

        // Status messages
        System.out.println("Train initialized successfully...");
        System.out.println("Initial Bogie Count : " + trainConsist.size());
        System.out.println("Current Train Consist : " + trainConsist);

        System.out.println("\nSystem ready for operations...");
    }
}