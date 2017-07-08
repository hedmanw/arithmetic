package se.wilhelmhedman.androidarithmetic.calc;

public class ArithmeticResponse {
    private String request;
    private String result;

    public ArithmeticResponse(String request, String result) {
        this.request = request;
        this.result = result;
    }

    @Override
    public String toString() {
        return request + "=\n" + result;
    }
}
