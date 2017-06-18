package se.wilhelmhedman.arithmetic.tree;

public class Multiplication extends Term {
    public Multiplication(Factor left, Term right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft().toString() + "*[" + getRight() + "]";
    }
}
