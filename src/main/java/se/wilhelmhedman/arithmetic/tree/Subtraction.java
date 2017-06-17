package se.wilhelmhedman.arithmetic.tree;

public class Subtraction extends Expression {
    public Subtraction(Term left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft() + "-" + getRight();
    }
}
