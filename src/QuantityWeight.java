import java.util.Objects;

public final class QuantityWeight {
    private final double value;
    private final WeightUnit unit;
    private static final double EPSILON = 1e-6;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        this.unit = unit;
    }

    public QuantityWeight convertTo(WeightUnit target) {
        if (target == null) throw new IllegalArgumentException();
        double base = unit.toBase(value);
        double converted = target.fromBase(base);
        return new QuantityWeight(converted, target);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        if (other == null || target == null) {
            throw new IllegalArgumentException();
        }

        double baseSum = this.unit.toBase(this.value)
                + other.unit.toBase(other.value);

        double result = target.fromBase(baseSum);
        return new QuantityWeight(result, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight other = (QuantityWeight) obj;

        double a = this.unit.toBase(this.value);
        double b = other.unit.toBase(other.value);

        return Math.abs(a - b) < EPSILON;
    }

    @Override
    public int hashCode() {
        double base = unit.toBase(value);
        return Objects.hash(Math.round(base / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}