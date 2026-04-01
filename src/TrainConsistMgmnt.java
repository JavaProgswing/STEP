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
    public static void performanceComparison(List<Bogie> bogies) {
        long startTime = System.nanoTime();
        for (Bogie bogie : bogies) {
            System.out.println(bogie);
        }
        long endTime = System.nanoTime();
        System.out.println("Loop Time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        bogies.stream().forEach(System.out::println);
        endTime = System.nanoTime();
        System.out.println("Stream Time: " + (endTime - startTime) + " ns");
    }

    public static void main(String[] args) {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 30));

        performanceComparison(bogies);
    }
}