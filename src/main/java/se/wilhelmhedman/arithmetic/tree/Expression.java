package se.wilhelmhedman.arithmetic.tree;

public abstract class Expression implements Evaluatable {
    private final Expression left;
    private final Term right;

    public Expression(Term term) {
        this.left = null;
        this.right = term;
    }

    public Expression(Expression  left, Term right) {
        this.left = left;
        this.right = right;
    }

    public Expression  getLeft() {
        return left;
    }

    public Term getRight() {
        return right;
    }
}
