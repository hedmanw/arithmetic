package se.wilhelmhedman.arithmetic.tree;

public abstract class Term {
    private final Factor left;
    private final Term right;

    public Term(Factor left) {
        this.left = left;
        this.right = null;
    }

    public Term(Factor left, Term right) {
        this.left = left;
        this.right = right;
    }

    public Factor getLeft() {
        return left;
    }

    public Term getRight() {
        return right;
    }
}
