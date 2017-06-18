package se.wilhelmhedman.arithmetic.tree;

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
    public double evaluate() {
        return expression.evaluate();
    }
}
