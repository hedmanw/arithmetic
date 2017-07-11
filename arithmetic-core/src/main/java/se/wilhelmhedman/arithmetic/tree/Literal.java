package se.wilhelmhedman.arithmetic.tree;

import java.math.BigDecimal;

public class Literal implements Evaluatable {
    private final BigDecimal value;

    public Literal(BigDecimal v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public BigDecimal evaluate() {
        return value;
    }
}
