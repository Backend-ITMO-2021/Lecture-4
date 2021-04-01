package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Negate implements Expression {
    private Expression operand;

    public Negate(Expression x) {
        operand = x;
    }

    @Override
    public int evaluate(int x) {
        return -operand.evaluate(x);
    }

    @Override
    public String toMiniString() {
        return null;
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return -operand.evaluateWithVariables(variables);
    }
}
