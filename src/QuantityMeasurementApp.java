public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toFeet(value);
        }

        private static QuantityLength fromBase(double base, LengthUnit target) {
            double v = target.fromFeet(base);
            return new QuantityLength(v, target);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            return fromBase(toBase(), targetUnit);
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) throw new IllegalArgumentException("Other cannot be null");
            double sum = this.toBase() + other.toBase();
            return fromBase(sum, this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) throw new IllegalArgumentException("Other cannot be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double sum = this.toBase() + other.toBase();
            return fromBase(sum, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static QuantityLength add(QuantityLength q1, QuantityLength q2) {
        if (q1 == null || q2 == null) throw new IllegalArgumentException("Null operand");
        return q1.add(q2);
    }

    public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null) throw new IllegalArgumentException("Null operand");
        return q1.add(q2, targetUnit);
    }

    public static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit targetUnit) {
        return new QuantityLength(v1, u1).add(new QuantityLength(v2, u2), targetUnit);
    }

    public static void main(String[] args) {
        System.out.println(add(new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.FEET));

        System.out.println(add(new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.INCH));

        System.out.println(add(new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.YARD));

        System.out.println(add(new QuantityLength(36.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.YARD),
                LengthUnit.FEET));
    }
}