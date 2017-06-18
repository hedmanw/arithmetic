package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;

public class Multiplication extends Term {
    public Multiplication(Term left, Factor right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft().toString() + "*[" + getRight() + "]";
    }

    @Override
    public BigDecimal evaluate() {
        return getLeft().evaluate().multiply(getRight().evaluate());
    }
}
