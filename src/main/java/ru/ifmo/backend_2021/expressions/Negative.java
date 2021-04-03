package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Negative extends Operation {
    private final Expression expression;

    public Negative(Expression expression) {
        super(4);
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "-" + expression.toString();
    }

    @Override
    public int evaluate(int x) {
        return -expression.evaluate(x);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return -expression.evaluateWithVariables(variables);
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }
}
