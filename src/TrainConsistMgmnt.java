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
    public static boolean checkSafetyCompliance(String cargoType) {
        return cargoType.equals("Flammable") || cargoType.equals("Non-Flammable");
    }

    public static void main(String[] args) {
        System.out.println("Is safety compliant: " + checkSafetyCompliance("Flammable"));
    }
}