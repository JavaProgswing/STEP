import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0) throw new ArithmeticException();
            return a / b;
        });

        private final DoubleBinaryOperator op;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.op = op;
        }

        double apply(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }

    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double base = perform(other, ArithmeticOperation.ADD);
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(base)), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit, true);
        double base = perform(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(base)), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validate(other, null, false);
        return perform(other, ArithmeticOperation.DIVIDE);
    }

    private void validate(Quantity<U> other, U targetUnit, boolean requireTarget) {
        if (other == null || other.unit == null) throw new IllegalArgumentException();
        if (unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException();
        if (requireTarget && targetUnit == null) throw new IllegalArgumentException();
        if (targetUnit != null && unit.getClass() != targetUnit.getClass()) throw new IllegalArgumentException();
        if (Double.isNaN(other.value) || Double.isInfinite(other.value)) throw new IllegalArgumentException();
    }

    private double perform(Quantity<U> other, ArithmeticOperation op) {
        double a = unit.convertToBaseUnit(value);
        double b = other.unit.convertToBaseUnit(other.value);
        return op.apply(a, b);
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}