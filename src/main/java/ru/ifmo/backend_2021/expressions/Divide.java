package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Divide extends HighPriorityExpression {
    public Divide(Expression v1, Expression v2) {
        super(v1, v2, '/');
    }

    @Override
    public int evaluate(int x) {
        return firstVariable.evaluate(x) / secondVariable.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return firstVariable.evaluateWithVariables(variables) / secondVariable.evaluateWithVariables(variables);
    }

    @Override
    public String toMiniString() {
        if (firstVariable instanceof BinaryExpression && secondVariable instanceof BinaryExpression) {
            return String.format("(%s) %s (%s)", firstVariable.toMiniString(), sign, secondVariable.toMiniString());
        }
        return super.toMiniString();
    }
}
