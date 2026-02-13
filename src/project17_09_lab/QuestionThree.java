package project17_09_lab;

class Bird {
    void fly() {
        System.out.println("Bird is flying");
    }
}

class Penguin extends Bird {
    @Override
    void fly() {
        System.out.println("Penguins can't fly");
    }
}

class Eagle extends Bird {
    @Override
    void fly() {
        System.out.println("Eagle is soaring high");
    }
}

public class QuestionThree {
    public static void main(String[] args) {
        Bird[] birds = {new Bird(), new Penguin(), new Eagle()};
        for (Bird bird : birds) {
            bird.fly();
        }
    }
}
