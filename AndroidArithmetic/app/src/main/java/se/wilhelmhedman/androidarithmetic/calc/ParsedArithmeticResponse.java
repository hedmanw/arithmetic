package se.wilhelmhedman.androidarithmetic.calc;

public class ParsedArithmeticResponse extends ArithmeticResponse {

    public ParsedArithmeticResponse(String request, String result) {
        super(request, result);
    }

    @Override
    public boolean hasErrors() {
        return false;
    }

    @Override
    public String toString() {
        return getRequest() + "\n= " + getResult();
    }
}
