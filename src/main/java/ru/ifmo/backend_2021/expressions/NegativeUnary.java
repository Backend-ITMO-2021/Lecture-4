package ru.ifmo.backend_2021.expressions;

import java.util.Objects;
public class NegativeUnary extends Expression implements Operator {
    public Expression exp;

    public NegativeUnary(Expression exp) {
        super(1);
        this.exp = exp;
    }

    @Override
    public int evaluate(int x) {
        return -exp.evaluate(x);
    }

    @Override
    public String toString() {
        return "-(" + exp.toMiniString() + ")";
    }

    @Override
    public String toMiniString() {
        if (exp instanceof Operand) {
            return "-" + exp.toMiniString();
        }
        return "-(" + exp.toMiniString() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(exp, "-");
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof NegativeUnary) {
            return exp.toMiniString() == expression.toMiniString();
        }
        return false;
    }
}
