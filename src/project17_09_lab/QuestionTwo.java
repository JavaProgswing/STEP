package project17_09_lab;

class Phone {
    String brand;
    String model;

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone " + brand + " " + model);
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
    }
}

class Smartphone extends Phone {
    String operatingSystem;

    public Smartphone(String brand, String model, String operatingSystem) {
        super(brand, model);
        this.operatingSystem = operatingSystem;
    }

    public Smartphone(String name, String operatingSystem) {
        super(name.split(",", 2)[0].trim(), name.split(",", 2)[1].trim());
        this.operatingSystem = operatingSystem;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Operating System: " + operatingSystem);
    }
}

public class QuestionTwo {
    public static void main(String[] args) {
        Smartphone smartphone1 = new Smartphone("Samsung", "Galaxy S21", "Android");
        smartphone1.displayInfo();

        System.out.println();

        Smartphone smartphone2 = new Smartphone("iPhone, 13 Pro", "iOS");
        smartphone2.displayInfo();
    }
}
