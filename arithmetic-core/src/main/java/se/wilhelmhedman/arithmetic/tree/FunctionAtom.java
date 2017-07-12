package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;

public abstract class FunctionAtom extends Factor {
    private final Atom atom;
    private String functionName;

    public FunctionAtom(Atom atom, String functionName) {
        this.atom = atom;
        this.functionName = functionName;
    }

    private double convertToDouble() {
        return atom.evaluate().doubleValue();
    }

    abstract double calculateFunctionValue(double value);

    @Override
    public String toString() {
        return functionName + "[" + atom.toString() + "]";
    }

    @Override
    public BigDecimal evaluate() {
        double functionValue = calculateFunctionValue(Math.toRadians(convertToDouble()));
        return new BigDecimal(functionValue);
    }

    public static class SinFunction extends FunctionAtom {
        public SinFunction(Atom atom) {
            super(atom, "sin");
        }

        @Override
        double calculateFunctionValue(double value) {
            return Math.sin(value);
        }
    }

    public static class CosFunction extends FunctionAtom {
        public CosFunction(Atom atom) {
            super(atom, "cos");
        }

        @Override
        double calculateFunctionValue(double value) {
            return Math.cos(value);
        }
    }

    public static class TanFunction extends FunctionAtom {
        public TanFunction(Atom atom) {
            super(atom, "tan");
        }

        @Override
        double calculateFunctionValue(double value) {
            return Math.tan(value);
        }
    }
}
