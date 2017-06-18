package se.wilhelmhedman.arithmetic.tree;

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
    public double evaluate() {
        return literal.evaluate();
    }
}
