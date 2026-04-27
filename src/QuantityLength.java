import java.util.Objects;

/**
 * Immutable quantity representing a length value and its unit.
 * Implements addition with an explicit target unit (UC7).
 */
public final class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("unit must not be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("value must be a finite number");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantityLength)) return false;
        QuantityLength that = (QuantityLength) o;
        return Double.doubleToLongBits(value) == Double.doubleToLongBits(that.value) && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    // --- Addition methods ---

    /**
     * Add another QuantityLength and return result in this instance's unit.
     * Backwards-compatible convenience method (implicit target = this.unit).
     */
    public QuantityLength add(QuantityLength other) {
        return add(this, other, this.unit);
    }

    /**
     * Add two quantities and return a new QuantityLength expressed in the explicitly
     * provided target unit. Implements UC7.
     * Validations: non-null args, valid units, finite numeric values.
     */
    public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {
        if (a == null || b == null) throw new IllegalArgumentException("Both quantities must be non-null");
        if (targetUnit == null) throw new IllegalArgumentException("targetUnit must be non-null");
        if (!Double.isFinite(a.value) || !Double.isFinite(b.value)) throw new IllegalArgumentException("Values must be finite numbers");

        // Convert both to base unit (feet)
        double aInFeet = a.value * a.unit.toFeetFactor();
        double bInFeet = b.value * b.unit.toFeetFactor();

        double sumFeet = aInFeet + bInFeet;

        // Convert sum from feet to target unit
        double resultInTarget = sumFeet / targetUnit.toFeetFactor();

        return new QuantityLength(resultInTarget, targetUnit);
    }

    /**
     * Optional helper to get value converted to a different unit.
     */
    public QuantityLength toUnit(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("targetUnit must be non-null");
        double valueInFeet = this.value * this.unit.toFeetFactor();
        double converted = valueInFeet / targetUnit.toFeetFactor();
        return new QuantityLength(converted, targetUnit);
    }
}

