package se.wilhelmhedman.androidarithmetic.calc;

public class ArithmeticErrorResponse extends ErrorResponse {
    public ArithmeticErrorResponse(String request, String result) {
        super(request, result);
    }

    @Override
    public int getOffendingIndex() {
        return getRequest().length();
    }
}
