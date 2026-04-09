import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

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

        bogies.sort(Comparator.comparing(Bogie::getBogieId));
        String[] ids = bogies.stream().map(Bogie::getBogieId).toArray(String[]::new);
        System.out.println("Sorted Bogie IDs: " + Arrays.toString(ids));

        // Determine target to search: use first CLI arg if provided, otherwise demo with "B102"
        String targetId = "B102";
        if (args.length > 0) {
            targetId = args[0];
        }

        int foundIndex = binarySearch(ids, targetId);
        if (foundIndex >= 0) {
            System.out.println("Bogie with ID " + targetId + " exists at index " + foundIndex + ".");
            System.out.println("Matching bogie: " + bogies.get(foundIndex));
        } else {
            System.out.println("Bogie with ID " + targetId + " does not exist.");
        }
    }


    private static int binarySearch(String[] arr, String target) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) return mid;
            if (cmp < 0) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }
}