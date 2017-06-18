package se.wilhelmhedman.arithmetic.tree;

public class Addition extends Expression {
    public Addition(Expression left, Term right) {
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
