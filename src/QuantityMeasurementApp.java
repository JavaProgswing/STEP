public class QuantityMeasurementApp {
    public static void main(String[] args) {
        // Demonstration of UC7 examples
        printExample(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        printExample(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        printExample(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        printExample(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        printExample(new QuantityLength(36.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.YARDS), LengthUnit.FEET);
        printExample(new QuantityLength(2.54, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS);
        printExample(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(0.0, LengthUnit.INCHES), LengthUnit.YARDS);
        printExample(new QuantityLength(5.0, LengthUnit.FEET), new QuantityLength(-2.0, LengthUnit.FEET), LengthUnit.INCHES);

        // Edge: null target unit should throw
        try {
            QuantityLength.add(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES), null);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception for null target unit: " + e.getMessage());
        }
    }

    private static void printExample(QuantityLength a, QuantityLength b, LengthUnit target) {
        QuantityLength result = QuantityLength.add(a, b, target);
        System.out.println("Input: add(" + a + ", " + b + ", " + target + ") -> Output: " + result);
    }
}

