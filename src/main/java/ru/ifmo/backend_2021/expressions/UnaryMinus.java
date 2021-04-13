package ru.ifmo.backend_2021.expressions;


import java.util.Map;
import java.util.Objects;

public class UnaryMinus implements Expression {
    private Expression expression;

    public UnaryMinus(Expression expression) {
        this.expression = expression;
    }

    public int evaluate(int x) {
        return -this.expression.evaluate(x);
    }

    public int evaluateWithVariables(Map<String, Integer> variables) {
        return -this.expression.evaluateWithVariables(variables);
    }

    public String toMiniString() {
        return this.toMiniString(0, "", false);
    }

    public String toMiniString(int parentPriority, String parentOperation, boolean atRight) {
        String format = "-%s";
        return String.format(format, this.expression.toMiniString());
    }

    public String toString() {
        return String.format("(-%s)", this.expression.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UnaryMinus) {
            return this.expression.equals(o);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash("-", this.expression);
    }
}