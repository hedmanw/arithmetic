package se.wilhelmhedman.arithmetic;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticLexer;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticParser;
import se.wilhelmhedman.arithmetic.tree.Expression;

public class Runner {
    public static void main(String[] args) {
        String input = "1+2+3";
        ANTLRInputStream inputStream = new ANTLRInputStream(input);
        ArithmeticLexer lexer = new ArithmeticLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArithmeticParser parser = new ArithmeticParser(tokens);

        ExpressionBuilder listener = new ExpressionBuilder();
        ParseTreeWalker.DEFAULT.walk(listener, parser.root());

        Expression exp = listener.getResult();
        System.out.println(exp.toString());
    }
}
