package se.wilhelmhedman.arithmetic.tree;

import se.wilhelmhedman.arithmetic.evaluation.EvaluationContext;

import java.math.BigDecimal;

public abstract class FunctionFactor extends Factor {
    protected final Atom atom;
    private String functionName;

    public FunctionFactor(Atom atom, String functionName) {
        this.atom = atom;
        this.functionName = functionName;
    }

    @Override
    public String toString() {
        return functionName + "[" + atom.toString() + "]";
    }

    protected double convertToDouble() {
        return atom.evaluate().doubleValue();
    }

    protected abstract double calculateFunctionValue(double value);

    @Override
    public BigDecimal evaluate() {
        double functionValue = calculateFunctionValue(convertToDouble());
        return new BigDecimal(functionValue);
    }

    public static class LnFunction extends FunctionFactor {
        public LnFunction(Atom atom) {
            super(atom, "ln");
        }

        @Override
        protected double calculateFunctionValue(double value) {
            return Math.log(value);
        }
    }

    public static class LogFunction extends FunctionFactor {
        public LogFunction(Atom atom) {
            super(atom, "log");
        }

        @Override
        protected double calculateFunctionValue(double value) {
            return Math.log10(value);
        }
    }
}
