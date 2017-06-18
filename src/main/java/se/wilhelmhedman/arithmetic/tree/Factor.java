package se.wilhelmhedman.arithmetic.tree;

import java.util.List;
import java.util.Stack;

public class Factor {
    private final Literal literal;
    private final Factor factor;

    public Factor(Literal literal) {
        this.literal = literal;
        this.factor = null;
    }

    public Factor(Literal literal, Factor factor) {
        this.literal = literal;
        this.factor = factor;
    }

    @Override
    public String toString() {
        if (factor == null) {
            return literal.toString();
        }
        else {
            return literal.toString() + "^[" + factor.toString() + ']';
        }
    }
}
