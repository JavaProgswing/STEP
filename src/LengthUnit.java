public enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.03280839895013123); // 1 cm = 0.03280839895 feet

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    /**
     * Returns the factor to multiply a value in this unit to get feet.
     */
    public double toFeetFactor() {
        return toFeetFactor;
    }
}

