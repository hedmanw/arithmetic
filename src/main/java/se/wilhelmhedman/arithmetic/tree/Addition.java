package se.wilhelmhedman.arithmetic.tree;

public class Addition extends Expression {
    public Addition(Term left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft() + "+[" + getRight() + "]";
    }

    @Override
    public double evaluate() {
        return getLeft().evaluate() + getRight().evaluate();
    }
}
