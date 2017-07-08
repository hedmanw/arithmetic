package se.wilhelmhedman.androidarithmetic.calc;

public class SyntaxErrorResponse extends ArithmeticResponse {
    private int offendingIndex;

    public SyntaxErrorResponse(String request, String result, int offendingIndex) {
        super(request, result);
        this.offendingIndex = offendingIndex;
    }

    @Override
    public boolean hasErrors() {
        return true;
    }

    @Override
    public String toString() {
        return getResult();
    }

    public int getOffendingIndex() {
        return offendingIndex;
    }
}
