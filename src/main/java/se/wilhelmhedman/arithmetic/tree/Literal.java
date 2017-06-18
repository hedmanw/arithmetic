package se.wilhelmhedman.arithmetic.tree;

public class Literal implements Evaluatable {
    private final double value;

    public Literal(double d) {
        this.value = d;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public double evaluate() {
        return value;
    }
}
