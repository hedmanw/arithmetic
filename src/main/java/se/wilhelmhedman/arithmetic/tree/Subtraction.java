package se.wilhelmhedman.arithmetic.tree;

public class Subtraction extends Expression {
    public Subtraction(Expression left, Term right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft() + "-[" + getRight() + "]";
    }

    @Override
    public double evaluate() {
        return getLeft().evaluate() - getRight().evaluate();
    }
}
