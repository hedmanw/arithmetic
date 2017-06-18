package se.wilhelmhedman.arithmetic;

import se.wilhelmhedman.arithmetic.antlr.ArithmeticBaseListener;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticParser;
import se.wilhelmhedman.arithmetic.tree.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionBuilder extends ArithmeticBaseListener {
    private final Map<ArithmeticParser.NumberContext, Literal> literals = new HashMap<>();
    private final Map<ArithmeticParser.AtomContext, Atom> atoms = new HashMap<>();
    private final Map<ArithmeticParser.FactorContext, Factor> factors = new HashMap<>();
    private final Map<ArithmeticParser.TermContext, Term> terms = new HashMap<>();
    private final Map<ArithmeticParser.ExpressionContext, Expression> expressions = new HashMap<>();
    private Expression resultExpression;

    @Override
    public void exitRoot(ArithmeticParser.RootContext ctx) {
        resultExpression = expressions.get(ctx.expression());
    }

    @Override
    public void exitSingleExpression(ArithmeticParser.SingleExpressionContext ctx) {
        SingleExpr expr = new SingleExpr(terms.get(ctx.term()));
        expressions.put(ctx, expr);
    }

    @Override
    public void exitTwoExpression(ArithmeticParser.TwoExpressionContext ctx) {
        Expression expression;
        if (ctx.PLUS() != null) {
            expression = new Addition(terms.get(ctx.term()), expressions.get(ctx.expression()));
        }
        else {
            expression = new Subtraction(terms.get(ctx.term()), expressions.get(ctx.expression()));
        }
        expressions.put(ctx, expression);
    }

    @Override
    public void exitSingleTerm(ArithmeticParser.SingleTermContext ctx) {
        SingleTerm term = new SingleTerm(factors.get(ctx.factor()));
        terms.put(ctx, term);
    }

    @Override
    public void exitTwoTerm(ArithmeticParser.TwoTermContext ctx) {
        Term term;
        if (ctx.TIMES() != null) {
            term = new Multiplication(factors.get(ctx.factor()), terms.get(ctx.term()));
        }
        else {
            term = new Division(factors.get(ctx.factor()), terms.get(ctx.term()));
        }
        terms.put(ctx, term);
    }

    @Override
    public void exitFactor(ArithmeticParser.FactorContext ctx) {
        Factor rootFactor;
        List<ArithmeticParser.AtomContext> atomsInFactor = ctx.atom();
        if (atomsInFactor.size() == 1) {
            rootFactor = new Factor(atoms.get(ctx.atom(0)));
        }
        else {
            Factor lastFactor = new Factor(atoms.get(atomsInFactor.get(atomsInFactor.size()-1)));
            for (int i = atomsInFactor.size() - 2; i >= 0; i--) {
                Factor factor = new Factor(atoms.get(atomsInFactor.get(i)), lastFactor);
                lastFactor = factor;
            }
            rootFactor = lastFactor;
        }
        factors.put(ctx, rootFactor);
    }

    @Override
    public void exitSingleAtom(ArithmeticParser.SingleAtomContext ctx) {
        Atom atom = new SingleAtom(literals.get(ctx.number()));
        atoms.put(ctx, atom);
    }

    @Override
    public void exitParenthesizedExpression(ArithmeticParser.ParenthesizedExpressionContext ctx) {
        Atom atom = new ParenthesizedAtom(expressions.get(ctx.expression()));
        atoms.put(ctx, atom);
    }

    @Override
    public void exitNumber(ArithmeticParser.NumberContext ctx) {
        Literal l = new Literal(Double.parseDouble(ctx.getText()));
        literals.put(ctx, l);
    }

    public Expression getResult() {
        return resultExpression;
    }
}
