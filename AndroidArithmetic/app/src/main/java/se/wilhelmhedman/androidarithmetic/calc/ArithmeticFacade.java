package se.wilhelmhedman.androidarithmetic.calc;

import se.wilhelmhedman.arithmetic.ExpressionRunner;

public class ArithmeticFacade {
    public static ArithmeticResponse execute(String input) {
        ArithmeticSyntaxTransformer ast = new ArithmeticSyntaxTransformer();
        String preprocessed = ast.transform(input);
        ExpressionRunner runner = new ExpressionRunner(preprocessed);

        String result = null;
        try {
            result = runner.evaluate();
        } catch (Exception e) {
            result = "Syntax error!";
        }
        return new ArithmeticResponse(input, result);
    }
}
