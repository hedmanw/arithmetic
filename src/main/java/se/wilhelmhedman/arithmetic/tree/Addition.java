package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;
import java.math.MathContext;

public class Addition extends Expression {
    public Addition(Expression left, Term right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft() + "+[" + getRight() + "]";
    }

    @Override
    public BigDecimal evaluate() {
        return getLeft().evaluate().add(getRight().evaluate(), MathContext.DECIMAL64);
    }
}
