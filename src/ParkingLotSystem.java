public class ParkingLotSystem {
    private final Spot[] table;
    private final int capacity;
    private int occupied = 0;
    private int totalProbes = 0;
    private int totalParks = 0;

    public ParkingLotSystem(int capacity) {
        this.capacity = capacity;
        this.table = new Spot[capacity];
    }

    public static void main(String[] args) throws InterruptedException {
        ParkingLotSystem lot = new ParkingLotSystem(10);
        System.out.println(lot.parkVehicle("ABC-1234"));
        System.out.println(lot.parkVehicle("ABC-1235"));
        System.out.println(lot.parkVehicle("XYZ-9999"));
        Thread.sleep(2000);
        System.out.println(lot.exitVehicle("ABC-1234"));
        lot.getStatistics();
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % capacity;
    }

    public String parkVehicle(String plate) {
        if (occupied == capacity)
            return "Parking Full!";

        int index = hash(plate);
        int probes = 0;
        while (table[index] != null) {
            index = (index + 1) % capacity;
            probes++;
        }

        Spot spot = new Spot();
        spot.licensePlate = plate;
        spot.entryTime = System.currentTimeMillis();

        table[index] = spot;
        occupied++;
        totalProbes += probes;
        totalParks++;

        return "Vehicle parked at spot #" + index +
                " (Probes: " + probes + ")";
    }

    public String exitVehicle(String plate) {
        int index = hash(plate);
        while (table[index] != null) {
            if (table[index].licensePlate.equals(plate)) {
                long durationMillis =
                        System.currentTimeMillis() - table[index].entryTime;
                double hours = durationMillis / 3600000.0;
                double fee = hours * 5;

                table[index] = null;
                occupied--;

                return "Vehicle exited. Duration: " +
                        String.format("%.2f", hours) +
                        " hours. Fee: $" +
                        String.format("%.2f", fee);
            }
            index = (index + 1) % capacity;
        }

        return "Vehicle not found.";
    }

    public void getStatistics() {
        double occupancyRate =
                (occupied * 100.0) / capacity;
        double avgProbes =
                totalParks == 0 ? 0 :
                        (totalProbes * 1.0) / totalParks;

        System.out.println("\n--- Parking Stats ---");
        System.out.println("Occupancy: " +
                String.format("%.2f", occupancyRate) + "%");
        System.out.println("Average Probes: " +
                String.format("%.2f", avgProbes));
    }

    private static class Spot {
        String licensePlate;
        long entryTime;
    }
}