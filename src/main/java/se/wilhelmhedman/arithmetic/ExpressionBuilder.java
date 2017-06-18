package se.wilhelmhedman.arithmetic;

import se.wilhelmhedman.arithmetic.antlr.ArithmeticBaseListener;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticParser;
import se.wilhelmhedman.arithmetic.tree.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionBuilder extends ArithmeticBaseListener {
    private final Map<ArithmeticParser.NumberContext, Literal> literals = new HashMap<>();
    private final Map<ArithmeticParser.FactorContext, Factor> factors = new HashMap<>();
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
        SingleTerm term = new SingleTerm(factors.get(ctx.factor()));
        terms.put(ctx, term);
        System.out.println("Visit singleNumber: " + term.toString());
    }

    @Override
    public void exitTwoNumber(ArithmeticParser.TwoNumberContext ctx) {
        Term term;
        if (ctx.TIMES() != null) {
            term = new Multiplication(factors.get(ctx.factor()), terms.get(ctx.term()));
        }
        else {
            term = new Division(factors.get(ctx.factor()), terms.get(ctx.term()));
        }
        System.out.println("Visit doubleNumber: " + term.toString());
        terms.put(ctx, term);
    }

    @Override
    public void exitFactor(ArithmeticParser.FactorContext ctx) {
        Factor rootFactor;
        List<ArithmeticParser.NumberContext> numbersInFactor = ctx.number();
        if (numbersInFactor.size() == 1) {
            rootFactor = new Factor(literals.get(ctx.number(0)));
        }
        else {
            Factor lastFactor = new Factor(literals.get(numbersInFactor.get(numbersInFactor.size()-1)));
            // bygg upp bakifrån
            for (int i = numbersInFactor.size() - 2; i >= 0; i--) {
                Factor factor = new Factor(literals.get(numbersInFactor.get(i)), lastFactor);
                lastFactor = factor;
            }
            rootFactor = lastFactor;
        }
        factors.put(ctx, rootFactor);
    }

    @Override
    public void exitNumber(ArithmeticParser.NumberContext ctx) {
        Literal l = new Literal(Double.parseDouble(ctx.getText()));
        System.out.println("Visit number: " + l);
        literals.put(ctx, l);
    }

    public Expression getResult() {
        return resultExpression;
    }
}
