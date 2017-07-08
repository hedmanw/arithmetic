package se.wilhelmhedman.androidarithmetic.calc;

public class ArithmeticSyntaxTransformer {

    public ArithmeticSyntaxTransformer() {
        // TODO: Caching
    }

    public String transform(String input) {
        String temp = input;
        temp = temp.replace('×', '*');
        temp = temp.replace('÷', '/');
        temp = temp.replace('+', '+');
        temp = temp.replace('−', '-');
        return temp;
    }
}
