package se.wilhelmhedman.arithmetic.tree;

public class SingleTerm extends Term {
    public SingleTerm(Factor factor) {
        super(factor);
    }

    @Override
    public String toString() {
        return getRight().toString();
    }

    @Override
    public double evaluate() {
        return getRight().evaluate();
    }
}
