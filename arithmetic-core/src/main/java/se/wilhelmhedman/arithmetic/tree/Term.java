package se.wilhelmhedman.arithmetic.tree;

public abstract class Term implements Evaluatable {
    private final Term left;
    private final Factor right;

    public Term(Factor factor) {
        this.left = null;
        this.right = factor;
    }

    public Term(Term left, Factor right) {
        this.left = left;
        this.right = right;
    }

    public Term getLeft() {
        return left;
    }

    public Factor getRight() {
        return right;
    }
}
