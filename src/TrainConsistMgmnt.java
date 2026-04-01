import java.util.ArrayList;
import java.util.Comparator;
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


class BogieCapacityComparator implements Comparator<Bogie> {
    @Override
    public int compare(Bogie b1, Bogie b2) {
        return Integer.compare(b2.getCapacity(), b1.getCapacity()); // Descending order
    }
}


public class TrainConsistMgmnt {
    public static void filterPassengerBogie(List<Bogie> bogies) {
        bogies.stream().filter(bogie -> bogie.getCapacity() > 50).forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 30));

        filterPassengerBogie(bogies);
    }
}