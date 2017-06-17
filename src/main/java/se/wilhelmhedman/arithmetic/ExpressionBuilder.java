package se.wilhelmhedman.arithmetic;

import se.wilhelmhedman.arithmetic.antlr.ArithmeticBaseListener;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticParser;
import se.wilhelmhedman.arithmetic.tree.*;

import java.util.HashMap;
import java.util.Map;

public class ExpressionBuilder extends ArithmeticBaseListener {
    private final Map<ArithmeticParser.NumberContext, Literal> literals = new HashMap<>();
    private final Map<ArithmeticParser.TermContext, Term> terms = new HashMap<>();
    private final Map<ArithmeticParser.ExpressionContext, Expression> expressions = new HashMap<>();
    private Expression resultExpression;

    @Override
    public void exitRoot(ArithmeticParser.RootContext ctx) {
        resultExpression = expressions.get(ctx.expression());
    }



    @Override
    public void exitSingleTerm(ArithmeticParser.SingleTermContext ctx) {
        SingleExpr expr = new SingleExpr(terms.get(ctx.term()));
        expressions.put(ctx, expr);
        System.out.println("Visit singleTerm: " + expr.toString());
    }

    @Override
    public void exitTwoTerm(ArithmeticParser.TwoTermContext ctx) {
        Expression expression;
        if (ctx.PLUS() != null) {
            expression = new Addition(terms.get(ctx.term()), expressions.get(ctx.expression()));
        }
        else {
            expression = new Subtraction(terms.get(ctx.term()), expressions.get(ctx.expression()));
        }
        System.out.println("Visit doubleTerm: " + expression.toString());
        expressions.put(ctx, expression);
    }

    @Override
    public void exitSingleNumber(ArithmeticParser.SingleNumberContext ctx) {
        SingleTerm term = new SingleTerm(literals.get(ctx.number()));
        terms.put(ctx, term);
        System.out.println("Visit singleNumber: " + term.toString());
    }

    @Override
    public void exitTwoNumber(ArithmeticParser.TwoNumberContext ctx) {
        Term term;
        if (ctx.TIMES() != null) {
            term = new Multiplication(literals.get(ctx.number()), terms.get(ctx.term()));
        }
        else {
            term = new Division(literals.get(ctx.number()), terms.get(ctx.term()));
        }
        System.out.println("Visit doubleNumber: " + term.toString());
        terms.put(ctx, term);
    }

    @Override
    public void exitNumber(ArithmeticParser.NumberContext ctx) {
        Literal l = new Literal(Double.parseDouble(ctx.DIGIT().toString()));
        System.out.println("Visit number: " + l);
        literals.put(ctx, l);
    }

    public Expression getResult() {
        return resultExpression;
    }
}
