import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Bogie {
    private String bogieId;
    private String name;
    private int capacity;

    public Bogie(String bogieId, String name, int capacity) {
        this.bogieId = bogieId;
        this.name = name;
        this.capacity = capacity;
    }

    public String getBogieId() {
        return bogieId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "ID:" + bogieId + " " + name + " (" + capacity + " seats)";
    }
}


public class TrainConsistMgmnt {
    public static void main(String[] args) {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("B101", "Sleeper", 72));
        bogies.add(new Bogie("B102", "AC Chair", 56));
        bogies.add(new Bogie("B103", "First Class", 30));

        String[] ids = bogies.stream().map(Bogie::getBogieId).toArray(String[]::new);
        System.out.println("Bogie IDs: " + Arrays.toString(ids));

        // Determine target to search: use first CLI arg if provided, otherwise demo with "B102"
        String targetId = "B102";
        if (args.length > 0) {
            targetId = args[0];
        }

        int foundIndex = linearSearch(ids, targetId);
        if (foundIndex >= 0) {
            System.out.println("Bogie with ID " + targetId + " exists at index " + foundIndex + ".");
        } else {
            System.out.println("Bogie with ID " + targetId + " does not exist.");
        }
    }

    private static int linearSearch(String[] arr, String target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) return i;
        }
        return -1;
    }
}