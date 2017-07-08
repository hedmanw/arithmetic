package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division extends Term {
    public Division(Term left, Factor right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return getLeft().toString() + "/[" + getRight() + "]";
    }

    @Override
    public BigDecimal evaluate() {
        return getLeft().evaluate().divide(getRight().evaluate(), 4, RoundingMode.HALF_UP);
    }
}
