import java.util.HashSet;
import java.util.LinkedList;

public class TrainConsistMgmnt {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" UC4 - Maintain Ordered Bogie Consist ");
        System.out.println("======================================\n");
        LinkedList<String> trainConsist = new LinkedList<>();
        HashSet<String> uniqueCheck = new HashSet<>();
        addBogie(trainConsist, uniqueCheck, "Engine");
        addBogie(trainConsist, uniqueCheck, "Sleeper");
        addBogie(trainConsist, uniqueCheck, "AC");
        addBogie(trainConsist, uniqueCheck, "Cargo");
        addBogie(trainConsist, uniqueCheck, "Guard");

        System.out.println("Initial Train Consist:");
        display(trainConsist);
        String pantry = "Pantry";
        if (!uniqueCheck.contains(pantry)) {
            trainConsist.add(1, pantry);
            uniqueCheck.add(pantry);
        }

        System.out.println("\nAfter inserting Pantry at position 2:");
        display(trainConsist);
        trainConsist.removeFirst(); // removes Engine
        trainConsist.removeLast();  // removes Guard

        System.out.println("\nAfter removing first and last bogie:");
        display(trainConsist);
    }

    private static void addBogie(LinkedList<String> list, HashSet<String> set, String bogie) {
        if (set.add(bogie)) {
            list.addLast(bogie);
        } else {
            System.out.println("Duplicate ignored: " + bogie);
        }
    }

    private static void display(LinkedList<String> list) {
        System.out.println(String.join(" -> ", list));
    }
}