package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Multiply extends HighPriorityExpression {
    public Multiply(Expression v1, Expression v2) {
        super(v1, v2, '*');
    }

    @Override
    public int evaluate(int x) {
        return firstVariable.evaluate(x) * secondVariable.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return firstVariable.evaluateWithVariables(variables) * secondVariable.evaluateWithVariables(variables);
    }

}
