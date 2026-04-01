import java.util.ArrayList;
import java.util.List;

class Bogie {
    private String name;
    private int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name + " (" + capacity + " seats)";
    }
}


public class TrainConsistMgmnt {
    public static void countTotalSeats(List<Bogie> bogies) {
        int totalSeats = bogies.stream().mapToInt(Bogie::getCapacity).reduce(0, Integer::sum);
        System.out.println("Total Seats in Train: " + totalSeats);
    }

    public static void main(String[] args) {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 30));

        countTotalSeats(bogies);
    }
}