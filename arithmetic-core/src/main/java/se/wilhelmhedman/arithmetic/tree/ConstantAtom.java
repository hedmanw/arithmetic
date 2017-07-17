package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;

public class ConstantAtom extends Atom {
    public static final ConstantAtom PI = new ConstantAtom(Math.PI, "pi");
    public static final ConstantAtom E = new ConstantAtom(Math.E, "e");

    private final BigDecimal value;
    private String name;

    private ConstantAtom(double value, String name) {
        this(new BigDecimal(value), name);
    }

    private ConstantAtom(BigDecimal value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public BigDecimal evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }

    public static class AnswerConstant extends ConstantAtom {
        private static AnswerConstant ANS = new AnswerConstant("0");

        private AnswerConstant(String value) {
            super(new BigDecimal(value), "ans");
        }

        public static AnswerConstant getInstance() {
            return ANS;
        }

        public static void setAns(String value) {
            ANS = new AnswerConstant(value);
        }
    }
}
