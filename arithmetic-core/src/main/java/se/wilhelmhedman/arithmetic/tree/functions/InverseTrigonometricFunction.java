package se.wilhelmhedman.arithmetic.tree.functions;

import se.wilhelmhedman.arithmetic.evaluation.EvaluationContext;
import se.wilhelmhedman.arithmetic.tree.Atom;
import se.wilhelmhedman.arithmetic.tree.FunctionFactor;

import java.math.BigDecimal;

public abstract class InverseTrigonometricFunction extends FunctionFactor {
    public InverseTrigonometricFunction(Atom atom, String functionName) {
        super(atom, functionName);
    }

    @Override
    public BigDecimal evaluate() {
        double functionValue = calculateFunctionValue(convertToDouble());
        if (EvaluationContext.getActiveContext().isUseDegrees()) {
            functionValue = Math.toDegrees(functionValue);
        }
        return new BigDecimal(functionValue);
    }

    public static class ASinFunction extends InverseTrigonometricFunction {
        public ASinFunction(Atom atom) {
            super(atom, "asin");
        }

        @Override
        protected double calculateFunctionValue(double value) {
            return Math.asin(value);
        }
    }

    public static class ACosFunction extends InverseTrigonometricFunction {
        public ACosFunction(Atom atom) {
            super(atom, "acos");
        }

        @Override
        protected double calculateFunctionValue(double value) {
            return Math.acos(value);
        }
    }

    public static class ATanFunction extends InverseTrigonometricFunction {
        public ATanFunction(Atom atom) {
            super(atom, "atan");
        }

        @Override
        protected double calculateFunctionValue(double value) {
            return Math.atan(value);
        }
    }



}
