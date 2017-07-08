package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;
import java.math.MathContext;

public class Subtraction extends Expression {
    public Subtraction(Expression left, Term right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft() + "-[" + getRight() + "]";
    }

    @Override
    public BigDecimal evaluate() {
        return getLeft().evaluate().subtract(getRight().evaluate(), MathContext.DECIMAL64);
    }
}
