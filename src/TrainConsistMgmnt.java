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
    public static void validateBogieCapacity(int capacity) throws InvalidCapacityException {
        if (capacity < 10) {
            throw new InvalidCapacityException("Capacity is too low!");
        }
    }

    public static void main(String[] args) {
        try {
            validateBogieCapacity(8);
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }
    }

    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }
}