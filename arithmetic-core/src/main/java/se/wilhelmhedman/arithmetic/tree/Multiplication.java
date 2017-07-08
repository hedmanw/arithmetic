package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;
import java.math.MathContext;

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
        return getLeft().evaluate().multiply(getRight().evaluate(), MathContext.DECIMAL64);
    }
}
