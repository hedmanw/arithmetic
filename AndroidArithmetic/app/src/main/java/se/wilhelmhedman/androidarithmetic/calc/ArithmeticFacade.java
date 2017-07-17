package se.wilhelmhedman.androidarithmetic.calc;

import se.wilhelmhedman.arithmetic.ExpressionRunner;
import se.wilhelmhedman.arithmetic.evaluation.EvaluationException;
import se.wilhelmhedman.arithmetic.evaluation.NumericException;

public class ArithmeticFacade {
    public static IArithmeticQuery execute(String input) {
        ArithmeticSyntaxTransformer ast = new ArithmeticSyntaxTransformer();
        String preprocessed = ast.transformToArithmeticDSL(input);
        ExpressionRunner runner = new ExpressionRunner(preprocessed, CalculationRepository.getPreviousAnswer());

        IArithmeticQuery response;
        try {
            String result = runner.evaluate();
            response = new ParsedArithmeticResponse(input, result);
            CalculationRepository.addResponse(response);
        } catch (EvaluationException e) {
            String reverseTransformedErrorMessage = ast.transformFromArithmeticDSL(e.getMessage());
            response = new SyntaxErrorResponse(input, reverseTransformedErrorMessage, e.getOffendingCharIndex());
        } catch (NumericException e) {
            response = new ArithmeticErrorResponse(input, e.getMessage());
        }

        return response;
    }
}
