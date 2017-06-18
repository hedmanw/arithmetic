package se.wilhelmhedman.arithmetic.tree;

public class Division extends Term {
    public Division(Factor left, Term right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft().toString() + "/[" + getRight() + "]";
    }
}
