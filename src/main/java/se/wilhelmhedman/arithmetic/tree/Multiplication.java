package se.wilhelmhedman.arithmetic.tree;

public class Multiplication extends Term {
    public Multiplication(Term left, Factor right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft().toString() + "*[" + getRight() + "]";
    }

    @Override
    public double evaluate() {
        return getLeft().evaluate() * getRight().evaluate();
    }
}
