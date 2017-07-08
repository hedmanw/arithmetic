package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;
import java.math.MathContext;

public class Factor implements Evaluatable {
    private final Atom atom;
    private final Factor factor;

    public Factor(Atom atom) {
        this.atom = atom;
        this.factor = null;
    }

    public Factor(Atom atom, Factor factor) {
        this.atom = atom;
        this.factor = factor;
    }

    @Override
    public String toString() {
        if (factor == null) {
            return atom.toString();
        }
        else {
            return atom.toString() + "^[" + factor.toString() + ']';
        }
    }

    @Override
    public BigDecimal evaluate() {
        if (factor == null) {
            return atom.evaluate();
        }
        else {
            // TODO: investigate non-integer powers using the formula X^(A+B)=X^A*X^B, where A is the integer part of the BigDecimal, and B the decimal part
            return atom.evaluate().pow(factor.evaluate().intValue(), MathContext.DECIMAL64);
        }
    }
}
