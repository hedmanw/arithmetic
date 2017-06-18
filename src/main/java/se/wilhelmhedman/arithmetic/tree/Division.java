package se.wilhelmhedman.arithmetic.tree;

public class Division extends Term {
    public Division(Term left, Factor right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft().toString() + "/[" + getRight() + "]";
    }

    @Override
    public double evaluate() {
        return getLeft().evaluate() / getRight().evaluate();
    }
}
