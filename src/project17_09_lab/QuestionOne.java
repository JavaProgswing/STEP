package project17_09_lab;

class Fruit {
    String name;
    String color;

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void displayInfo() {
        System.out.println("Fruit Name: " + name);
        System.out.println("Color: " + color);
    }
}

class Apple extends Fruit {
    String variety;

    public Apple(String name, String color, String variety) {
        super(name, color);
        this.variety = variety;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Variety: " + variety);
    }
}

public class QuestionOne {
    public static void main(String[] args) {
        Apple apple = new Apple("Apple", "Red", "Himalayan");
        apple.displayInfo();
    }
}
