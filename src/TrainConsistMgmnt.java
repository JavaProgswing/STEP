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
    public static boolean validateTrainId(String trainId) {
        return trainId.matches("[A-Z]{2}\\d{4}");
    }

    public static void main(String[] args) {
        System.out.println("Valid Train ID: " + validateTrainId("AB1234"));
    }
}