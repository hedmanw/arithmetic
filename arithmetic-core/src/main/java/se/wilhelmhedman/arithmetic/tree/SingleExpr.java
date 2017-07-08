package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;

public class SingleExpr extends Expression {
    public SingleExpr(Term literal) {
        super(literal);
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
