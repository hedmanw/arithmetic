package se.wilhelmhedman.arithmetic.tree.functions;

import se.wilhelmhedman.arithmetic.evaluation.EvaluationContext;
import se.wilhelmhedman.arithmetic.tree.Atom;
import se.wilhelmhedman.arithmetic.tree.FunctionFactor;

public abstract class TrigFunction extends FunctionFactor {
    public TrigFunction(Atom atom, String functionName) {
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

    public static class SinFunction extends TrigFunction {
        public SinFunction(Atom atom) {
            super(atom, "sin");
        }

        @Override
        protected double calculateFunctionValue(double value) {
            return Math.sin(value);
        }
    }

    public static class CosFunction extends TrigFunction {
        public CosFunction(Atom atom) {
            super(atom, "cos");
        }

        @Override
        protected double calculateFunctionValue(double value) {
            return Math.cos(value);
        }
    }

    public static class TanFunction extends TrigFunction {
        public TanFunction(Atom atom) {
            super(atom, "tan");
        }

        @Override
        protected double calculateFunctionValue(double value) {
            return Math.tan(value);
        }
    }
}
