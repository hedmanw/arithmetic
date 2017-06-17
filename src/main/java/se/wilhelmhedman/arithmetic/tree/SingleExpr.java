package se.wilhelmhedman.arithmetic.tree;

public class SingleExpr extends Expression {
    public SingleExpr(Term literal) {
        super(literal);
    }

    @Override
    public String toString() {
        return getLeft().toString();
    }
}
