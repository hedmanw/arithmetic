package se.wilhelmhedman.arithmetic;

import se.wilhelmhedman.arithmetic.antlr.ArithmeticBaseListener;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticErrorListener;
import se.wilhelmhedman.arithmetic.antlr.ArithmeticParser;
import se.wilhelmhedman.arithmetic.tree.*;

import java.math.BigDecimal;
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
    private ArithmeticErrorListener arithmeticErrorListener;

    public ExpressionBuilder(ArithmeticErrorListener arithmeticErrorListener) {
        this.arithmeticErrorListener = arithmeticErrorListener;
    }

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
            expression = new Addition(expressions.get(ctx.expression()), terms.get(ctx.term()));
        }
        else {
            expression = new Subtraction(expressions.get(ctx.expression()), terms.get(ctx.term()));
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
            term = new Multiplication(terms.get(ctx.term()), factors.get(ctx.factor()));
        }
        else {
            term = new Division(terms.get(ctx.term()), factors.get(ctx.factor()));
        }
        terms.put(ctx, term);
    }

    @Override
    public void exitPowerFactor(ArithmeticParser.PowerFactorContext ctx) {
        PowerFactor rootFactor;
        List<ArithmeticParser.AtomContext> atomsInFactor = ctx.atom();
        if (atomsInFactor.size() == 1) {
            rootFactor = new PowerFactor(atoms.get(ctx.atom(0)));
        }
        else {
            PowerFactor lastFactor = new PowerFactor(atoms.get(atomsInFactor.get(atomsInFactor.size()-1)));
            for (int i = atomsInFactor.size() - 2; i >= 0; i--) {
                PowerFactor factor = new PowerFactor(atoms.get(atomsInFactor.get(i)), lastFactor);
                lastFactor = factor;
            }
            rootFactor = lastFactor;
        }
        factors.put(ctx, rootFactor);
    }

    @Override
    public void exitFunctionFactor(ArithmeticParser.FunctionFactorContext ctx) {
        FunctionFactor atom = null;
        Atom functionArgument = atoms.get(ctx.atom());
        if (ctx.SIN() != null) {
            atom = new FunctionFactor.SinFunction(functionArgument);
        }
        else if (ctx.COS() != null) {
            atom = new FunctionFactor.CosFunction(functionArgument);
        }
        else if (ctx.TAN() != null){
            atom = new FunctionFactor.TanFunction(functionArgument);
        }
        else if (ctx.LN() != null) {
            atom = new FunctionFactor.LnFunction(functionArgument);
        }
        else if (ctx.LOG() != null) {
            atom = new FunctionFactor.LogFunction(functionArgument);
        }
        factors.put(ctx, atom);
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
    public void exitConstantAtom(ArithmeticParser.ConstantAtomContext ctx) {
        Atom atom = null;
        if (ctx.PI() != null) {
            atom = ConstantAtom.PI;
        }
        else if (ctx.E() != null) {
            atom = ConstantAtom.E;
        }
        atoms.put(ctx, atom);
    }

    @Override
    public void exitNumber(ArithmeticParser.NumberContext ctx) {
        String text = ctx.getText();
        try {
            BigDecimal bigDecimal = new BigDecimal(text);
            Literal l = new Literal(bigDecimal);
            literals.put(ctx, l);
        } catch (Exception e) {
            arithmeticErrorListener.syntaxError(null, null, 0, ctx.getStart().getCharPositionInLine(), "Bad number: (" + text + ")", null);
        }
    }

    public Expression getResult() {
        return resultExpression;
    }
}
