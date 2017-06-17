package se.wilhelmhedman.arithmetic.tree;

public class SingleTerm extends Term {
    public SingleTerm(Literal literal) {
        super(literal);
    }

    @Override
    public String toString() {
        return getLeft().toString();
    }
}
