package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;

public class ParenthesizedAtom extends Atom {
    private final Expression expression;

    public ParenthesizedAtom(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "(" + expression.toString() + ")";
    }

    @Override
    public BigDecimal evaluate() {
        return expression.evaluate();
    }
}
