package project17_09_lab;

class Instrument {
    String name, material;

    public Instrument(String name, String material) {
        this.name = name;
        this.material = material;
    }

    public void displayInfo() {
        System.out.println("Instrument Name: " + name);
        System.out.println("Material: " + material);
    }
}

class Piano extends Instrument {
    int numberOfKeys;

    public Piano(String name, String material, int numberOfKeys) {
        super(name, material);
        this.numberOfKeys = numberOfKeys;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of Keys: " + numberOfKeys);
    }
}

class Guitar extends Instrument {
    int numberOfStrings;

    public Guitar(String name, String material, int numberOfStrings) {
        super(name, material);
        this.numberOfStrings = numberOfStrings;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of Strings: " + numberOfStrings);
    }
}

class Drum extends Instrument {
    String drumType;

    public Drum(String name, String material, String drumType) {
        super(name, material);
        this.drumType = drumType;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Drum Type: " + drumType);
    }
}

public class QuestionFive {
    public static void main(String[] args) {
        Instrument[] i = {new Piano("Grand Piano", "Wood", 88), new Guitar("Acoustic Guitar", "Wood", 6), new Drum("Bass Drum", "Metal", "Bass")};
        for (Instrument instrument : i) {
            instrument.displayInfo();
            System.out.println();
        }
    }
}
