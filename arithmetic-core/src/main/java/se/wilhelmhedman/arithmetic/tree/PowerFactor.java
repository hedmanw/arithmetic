package se.wilhelmhedman.arithmetic.tree;

import se.wilhelmhedman.arithmetic.evaluation.EvaluationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PowerFactor extends Factor {
    private final Atom atom;
    private final Factor factor;

    public PowerFactor(Atom atom) {
        this.atom = atom;
        this.factor = null;
    }

    public PowerFactor(Atom atom, Factor factor) {
        this.atom = atom;
        this.factor = factor;
    }

    @Override
    public String toString() {
        if (factor == null) {
            return atom.toString();
        }
        else {
            return atom.toString() + "^[" + factor.toString() + ']';
        }
    }

    @Override
    public BigDecimal evaluate() {
        BigDecimal thisEvaluated = atom.evaluate();
        if (factor == null) {
            return thisEvaluated;
        }
        else {
            BigDecimal factorEvaluated = factor.evaluate();
            BigDecimal result;

            int signOf2 = factorEvaluated.signum();
            // Perform X^(A+B)=X^A*X^B (B = remainder)
            double dn1 = thisEvaluated.doubleValue();
            // Compare the same row of digits according to context
//            if (!CalculatorUtils.isEqual(n1, dn1)) {
//                throw new Exception(); // Cannot convert n1 to double
//            }
            factorEvaluated = factorEvaluated.multiply(new BigDecimal(signOf2)); // factorEvaluated is now positive
            BigDecimal remainderOf2 = factorEvaluated.remainder(BigDecimal.ONE);
            BigDecimal n2IntPart = factorEvaluated.subtract(remainderOf2);
            // Calculate big part of the power using context -
            // bigger range and performance but lower accuracy
            BigDecimal intPow = thisEvaluated.pow(n2IntPart.intValueExact(), EvaluationContext.DEFAULT_CONTEXT);
            BigDecimal doublePow = new BigDecimal(Math.pow(dn1, remainderOf2.doubleValue()));
            result = intPow.multiply(doublePow);

            // Fix negative power
            if (signOf2 == -1) {
                result = BigDecimal.ONE.divide(result, EvaluationContext.DEFAULT_SCALE, RoundingMode.HALF_UP);
            }
            return result;
        }
    }
}
