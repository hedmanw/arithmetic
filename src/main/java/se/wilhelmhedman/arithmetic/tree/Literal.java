package se.wilhelmhedman.arithmetic.tree;

public class Literal {
    private final double value;

    public Literal(double d) {
        this.value = d;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
