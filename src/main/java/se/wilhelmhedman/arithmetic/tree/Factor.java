package se.wilhelmhedman.arithmetic.tree;

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
    public double evaluate() {
        if (factor == null) {
            return atom.evaluate();
        }
        else {
            return Math.pow(atom.evaluate(), factor.evaluate());
        }
    }
}
