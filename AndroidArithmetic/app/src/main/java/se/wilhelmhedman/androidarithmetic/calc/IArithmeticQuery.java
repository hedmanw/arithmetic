package se.wilhelmhedman.androidarithmetic.calc;

public interface IArithmeticQuery {
    String getRequest();
    String getResult();
    boolean hasErrors();
}
