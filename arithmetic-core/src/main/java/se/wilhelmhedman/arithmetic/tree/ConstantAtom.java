package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;

public class ConstantAtom extends Atom {
    public static final ConstantAtom PI = new ConstantAtom(Math.PI, "pi");
    public static final ConstantAtom E = new ConstantAtom(Math.E, "e");

    private final BigDecimal value;
    private String name;

    private ConstantAtom(double value, String name) {
        this.value = new BigDecimal(value);
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
}
