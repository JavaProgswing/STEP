import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private void validate(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Null quantity");
        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    public Quantity<U> convertTo(U target) {
        if (target == null) throw new IllegalArgumentException("Null target unit");
        double base = toBase();
        double converted = target.convertFromBaseUnit(base);
        return new Quantity<>(round(converted), target);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        validate(other);
        if (target == null) throw new IllegalArgumentException("Null target unit");

        double resultBase = this.toBase() + other.toBase();
        double result = target.convertFromBaseUnit(resultBase);
        return new Quantity<>(round(result), target);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target) {
        validate(other);
        if (target == null) throw new IllegalArgumentException("Null target unit");

        double resultBase = this.toBase() - other.toBase();
        double result = target.convertFromBaseUnit(resultBase);
        return new Quantity<>(round(result), target);
    }

    public double divide(Quantity<U> other) {
        validate(other);
        double divisor = other.toBase();
        if (divisor == 0.0) throw new ArithmeticException("Division by zero");

        return this.toBase() / divisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity<?> that)) return false;

        if (!unit.getClass().equals(that.unit.getClass())) return false;

        double thisBase = this.toBase();
        double thatBase = ((Quantity<U>) that).toBase();

        return Double.compare(thisBase, thatBase) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round(toBase()), unit.getClass());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}