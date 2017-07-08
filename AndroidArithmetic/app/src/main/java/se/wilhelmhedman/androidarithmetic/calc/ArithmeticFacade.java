package se.wilhelmhedman.androidarithmetic.calc;

import se.wilhelmhedman.arithmetic.ExpressionRunner;
import se.wilhelmhedman.arithmetic.evaluation.EvaluationException;

public class ArithmeticFacade {
    public static IArithmeticQuery execute(String input) {
        ArithmeticSyntaxTransformer ast = new ArithmeticSyntaxTransformer();
        String preprocessed = ast.transform(input);
        ExpressionRunner runner = new ExpressionRunner(preprocessed);

        IArithmeticQuery response;
        try {
            String result = runner.evaluate();
            response = new ParsedArithmeticResponse(input, result);
            CalculationRepository.addResponse(response);
        } catch (EvaluationException e) {
            response = new SyntaxErrorResponse(input, e.getMessage(), e.getOffendingCharIndex());
        }

        return response;
    }
}
