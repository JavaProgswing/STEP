package project17_09_lab;

class Box {
    void pack() {
        System.out.println("Packing items in a box");
    }

    void unpack() {
        System.out.println("Unpacking items from a box");
    }
}

class GiftBox extends Box {
    @Override
    void pack() {
        System.out.println("Packing items in a gift box with decorative wrapping");
    }

    @Override
    void unpack() {
        System.out.println("Unpacking items from a gift box carefully");
    }
}

public class QuestionSix {
    public static void main(String[] args) {
        Box box = new Box();
        box.pack();
        box.unpack();
        GiftBox giftBox = new GiftBox();
        giftBox.pack();
        giftBox.unpack();
    }
}
