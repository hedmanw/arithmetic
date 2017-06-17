package se.wilhelmhedman.arithmetic.tree;

public abstract class Term {
    private final Literal left;
    private final Term right;

    public Term(Literal left) {
        this.left = left;
        this.right = null;
    }

    public Term(Literal left, Term right) {
        this.left = left;
        this.right = right;
    }

    public Literal getLeft() {
        return left;
    }

    public Term getRight() {
        return right;
    }
}
