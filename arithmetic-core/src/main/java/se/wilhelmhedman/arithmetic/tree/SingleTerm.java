package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;

public class SingleTerm extends Term {
    public SingleTerm(Factor factor) {
        super(factor);
    }

    @Override
    public String toString() {
        return getRight().toString();
    }

    @Override
    public BigDecimal evaluate() {
        return getRight().evaluate();
    }
}
