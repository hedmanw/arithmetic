package se.wilhelmhedman.arithmetic.evaluation;

import java.math.MathContext;

public class EvaluationContext {
    public static final MathContext DEFAULT_CONTEXT = MathContext.DECIMAL32;
    public static final int DEFAULT_SCALE = 5;
    private static EvaluationContext activeContext = new EvaluationContext(true, DEFAULT_CONTEXT, DEFAULT_SCALE);

    private final boolean useDegrees;
    private final MathContext mathContext;
    private final int scale;

    private EvaluationContext(boolean useDegrees, MathContext mathContext, int scale) {
        this.useDegrees = useDegrees;
        this.mathContext = mathContext;
        this.scale = scale;
    }

    public boolean isUseDegrees() {
        return useDegrees;
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    public int getScale() {
        return scale;
    }

    public static EvaluationContext getActiveContext() {
        return activeContext;
    }

    public static void setActiveContext(EvaluationContext context) {
        activeContext = context;
    }

    public static class EvaluationContextBuilder {
        private boolean nestedUseDegrees = false;
        private MathContext nestedMathContext = DEFAULT_CONTEXT;
        private int nestedScale = DEFAULT_SCALE;

        public EvaluationContextBuilder setUseDegrees(final boolean useDegrees) {
            this.nestedUseDegrees = useDegrees;
            return this;
        }

        public EvaluationContextBuilder setMathContext(final MathContext mathContext) {
            this.nestedMathContext = mathContext;
            return this;
        }

        public EvaluationContextBuilder setScale(final int scale) {
            this.nestedScale = scale;
            return this;
        }

        public EvaluationContext build() {
            return new EvaluationContext(nestedUseDegrees, nestedMathContext, nestedScale);
        }
    }
}
