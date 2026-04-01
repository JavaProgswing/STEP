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
    public static void cargoAssignment(String cargoType) {
        try {
            if (cargoType == null) throw new NullPointerException("Cargo type cannot be null");
            System.out.println("Cargo type: " + cargoType);
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Cargo assignment process completed.");
        }
    }

    public static void main(String[] args) {
        cargoAssignment("Flammable");
    }
}