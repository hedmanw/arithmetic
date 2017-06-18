package se.wilhelmhedman.arithmetic.tree;

public class SingleExpr extends Expression {
    public SingleExpr(Term literal) {
        super(literal);
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
