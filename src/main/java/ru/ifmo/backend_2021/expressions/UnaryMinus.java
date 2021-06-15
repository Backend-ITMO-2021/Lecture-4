package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class UnaryMinus extends UnaryExpression{
    private final Expression value;

    public UnaryMinus(Expression value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return -this.value.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return -this.value.evaluateWithVariables(variables);
    }

    @Override
    public String toString() {
        return "-" + this.value.toMiniString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UnaryMinus) {
            return value.equals(((UnaryMinus) o).value);
        }
        return false;
    }
}