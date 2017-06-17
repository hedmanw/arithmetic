package se.wilhelmhedman.arithmetic.tree;

public abstract class Expression {
    private final Term left;
    private final Expression right;

    public Expression(Term term) {
        this.left = term;
        this.right = null;
    }

    public Expression(Term left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Term getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
