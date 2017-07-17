package se.wilhelmhedman.androidarithmetic.calc;

public class SyntaxErrorResponse extends ErrorResponse {
    private int offendingIndex;

    public SyntaxErrorResponse(String request, String result, int offendingIndex) {
        super(request, result);
        this.offendingIndex = offendingIndex;
    }

    public int getOffendingIndex() {
        return offendingIndex;
    }
}
