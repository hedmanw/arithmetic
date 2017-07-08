package se.wilhelmhedman.androidarithmetic.calc;


public abstract class ArithmeticResponse implements IArithmeticQuery {
    private String request;
    private String result;

    public ArithmeticResponse(String request, String result) {
        this.request = request;
        this.result = result;
    }

    @Override
    public String getRequest() {
        return request;
    }

    @Override
    public String getResult() {
        return result;
    }
}
