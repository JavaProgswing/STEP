package dev;

class ParkingSpot {
    String plate;
    long entryTime;
    boolean deleted;
}

class ParkingLot {
    private ParkingSpot[] table;
    private int size;

    public ParkingLot(int capacity) {
        table = new ParkingSpot[capacity];
        size = capacity;
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    public void park(String plate) {
        int idx = hash(plate);

        for (int i = 0; i < size; i++) {
            int probe = (idx + i) % size;

            if (table[probe] == null || table[probe].deleted) {
                ParkingSpot p = new ParkingSpot();
                p.plate = plate;
                p.entryTime = System.currentTimeMillis();
                table[probe] = p;
                System.out.println("Parked at " + probe);
                return;
            }
        }
    }

    public void exit(String plate) {
        int idx = hash(plate);

        for (int i = 0; i < size; i++) {
            int probe = (idx + i) % size;
            ParkingSpot p = table[probe];

            if (p == null) return;
            if (!p.deleted && p.plate.equals(plate)) {
                long duration = System.currentTimeMillis() - p.entryTime;
                p.deleted = true;
                System.out.println("Exited. Duration: " + duration / 1000 + "s");
                return;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(500);
        lot.park("ABC123");
        lot.park("XYZ999");
        lot.exit("ABC123");
    }
}
