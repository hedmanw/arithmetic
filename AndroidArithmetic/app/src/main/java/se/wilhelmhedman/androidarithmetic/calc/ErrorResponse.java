package se.wilhelmhedman.androidarithmetic.calc;

public abstract class ErrorResponse extends ArithmeticResponse {

    public ErrorResponse(String request, String result) {
        super(request, result);
    }

    @Override
    public String toString() {
        return getResult();
    }

    @Override
    public boolean hasErrors() {
        return true;
    }

    public abstract int getOffendingIndex();
}
