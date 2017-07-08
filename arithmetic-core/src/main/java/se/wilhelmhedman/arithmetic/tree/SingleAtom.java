package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;

public class SingleAtom extends Atom {
    private final Literal literal;

    public SingleAtom(Literal literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal.toString();
    }

    @Override
    public BigDecimal evaluate() {
        return literal.evaluate();
    }
}
