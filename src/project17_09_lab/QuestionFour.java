package project17_09_lab;

class Color {
    String name;

    public Color(String name) {
        this.name = name;
    }

    public void displayInfo() {
        System.out.println("Color Name: " + name);
    }
}

class PrimaryColor extends Color {
    int intensity;

    public PrimaryColor(String name, int intensity) {
        super(name);
        this.intensity = intensity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Intensity: " + intensity);
    }
}

class Red extends PrimaryColor {
    String shade;

    public Red(int intensity, String shade) {
        super("Red", intensity);
        this.shade = shade;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Shade: " + shade);
    }
}

public class QuestionFour {
    public static void main(String[] args) {
        Red red = new Red(80, "Crimson");
        red.displayInfo();
        PrimaryColor primaryColor = new PrimaryColor("Green", 75);
        primaryColor.displayInfo();
    }
}
