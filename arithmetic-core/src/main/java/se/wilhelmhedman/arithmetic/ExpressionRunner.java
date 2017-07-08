package se.wilhelmhedman.arithmetic;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticLexer;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticParser;
import se.wilhelmhedman.arithmetic.tree.Expression;

import java.math.BigDecimal;
import java.math.MathContext;

public class ExpressionRunner {
    public static final BigDecimal ONE_MILLION = new BigDecimal(1000000, MathContext.DECIMAL64);
    private final String input;

    public ExpressionRunner(String input) {
        this.input = input;
    }

    protected Expression getRoot() {
        ANTLRInputStream inputStream = new ANTLRInputStream(input);
        ArithmeticLexer lexer = new ArithmeticLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArithmeticParser parser = new ArithmeticParser(tokens);

        ExpressionBuilder listener = new ExpressionBuilder();
        ParseTreeWalker.DEFAULT.walk(listener, parser.root());

        return listener.getResult();
    }

    public String evaluate() {
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
