import java.util.ArrayList;
import java.util.List;

public class TrainConsistMgmnt {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println(" UC2 - Add Passenger Bogies to Train ");
        System.out.println("========================================\n");
        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        System.out.println("After adding bogies:");
        System.out.println("Passenger Bogies: " + passengerBogies);
        passengerBogies.remove("AC Chair");

        System.out.println("\nAfter removing AC Chair:");
        System.out.println("Passenger Bogies: " + passengerBogies);
        boolean exists = passengerBogies.contains("Sleeper");

        System.out.println("\nChecking if 'Sleeper' exists:");
        System.out.println("Sleeper present? " + exists);
        System.out.println("\nFinal Train Consist:");
        System.out.println(passengerBogies);

        System.out.println("\nSystem ready for next operation...");
    }
}