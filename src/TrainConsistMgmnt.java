import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public static void groupBogieByType(List<Bogie> bogies) {
        Map<String, List<Bogie>> groupedBogie = bogies.stream().collect(Collectors.groupingBy(bogie -> bogie.getName().split(" ")[0]));
        groupedBogie.forEach((type, list) -> System.out.println(type + ": " + list));
    }

    public static void main(String[] args) {
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 30));

        groupBogieByType(bogies);
    }
}