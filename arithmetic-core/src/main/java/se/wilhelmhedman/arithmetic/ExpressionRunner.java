package se.wilhelmhedman.arithmetic;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticErrorListener;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticLexer;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticParser;
import se.wilhelmhedman.arithmetic.evaluation.EvaluationException;
import se.wilhelmhedman.arithmetic.tree.Expression;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class ExpressionRunner {
    public static final BigDecimal ONE_MILLION = new BigDecimal(1000000, MathContext.DECIMAL64);
    private final String input;

    public ExpressionRunner(String input) {
        this.input = input;
    }

    protected Expression getRoot() throws EvaluationException {
        ANTLRInputStream inputStream = new ANTLRInputStream(input);
        ArithmeticLexer lexer = new ArithmeticLexer(inputStream);
//        lexer.removeErrorListeners();
//        lexer.addErrorListener(new ArithmeticErrorListener());
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArithmeticParser parser = new ArithmeticParser(tokens);
        ArithmeticErrorListener errorListener = new ArithmeticErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        ExpressionBuilder listener = new ExpressionBuilder(errorListener);

        ParseTreeWalker.DEFAULT.walk(listener, parser.root());

        List<ArithmeticErrorListener.SyntaxError> syntaxErrors = errorListener.getSyntaxErrors();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            ArithmeticErrorListener.SyntaxError syntaxError = syntaxErrors.get(0);
            throw new EvaluationException(syntaxError.getCharPositionInLine(), syntaxError.getMessage());
        }

        return listener.getResult();
    }

    public String evaluate() throws EvaluationException {
        Expression rootExp = getRoot();
        BigDecimal result = rootExp.evaluate().stripTrailingZeros();

        String resultString;
        if (result.abs(MathContext.DECIMAL64).compareTo(ONE_MILLION) >= 0) {
            resultString = result.toString();
        }
        else {
            resultString = result.toPlainString();
        }
        return resultString;
    }
}
