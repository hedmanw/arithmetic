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

    abstract double calculateFunctionValue(double value);

    @Override
    public BigDecimal evaluate() {
        double functionValue = calculateFunctionValue(convertToDouble());
        return new BigDecimal(functionValue);
    }

    public abstract static class TrigonometricFunction extends FunctionFactor {
        public TrigonometricFunction(Atom atom, String functionName) {
            super(atom, functionName);
        }

        @Override
        protected double convertToDouble() {
            double d;

            if (EvaluationContext.getActiveContext().isUseDegrees()) {
                d = Math.toRadians(super.convertToDouble());
            }
            else {
                d = super.convertToDouble();
            }

            return d;
        }
    }

    public static class SinFunction extends TrigonometricFunction {
        public SinFunction(Atom atom) {
            super(atom, "sin");
        }

        @Override
        double calculateFunctionValue(double value) {
            return Math.sin(value);
        }
    }

    public static class CosFunction extends TrigonometricFunction {
        public CosFunction(Atom atom) {
            super(atom, "cos");
        }

        @Override
        double calculateFunctionValue(double value) {
            return Math.cos(value);
        }
    }

    public static class TanFunction extends TrigonometricFunction {
        public TanFunction(Atom atom) {
            super(atom, "tan");
        }

        @Override
        double calculateFunctionValue(double value) {
            return Math.tan(value);
        }
    }

    public static class LnFunction extends FunctionFactor {
        public LnFunction(Atom atom) {
            super(atom, "ln");
        }

        @Override
        double calculateFunctionValue(double value) {
            return Math.log(value);
        }
    }

    public static class LogFunction extends FunctionFactor {
        public LogFunction(Atom atom) {
            super(atom, "log");
        }

        @Override
        double calculateFunctionValue(double value) {
            return Math.log10(value);
        }
    }
}
