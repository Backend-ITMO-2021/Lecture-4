package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Add extends BinaryExpression {
    public Add(Expression v1, Expression v2) {
        super(v1, v2, '+');
    }

    @Override
    public int evaluate(int x) {
        return firstVariable.evaluate(x) + secondVariable.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return firstVariable.evaluateWithVariables(variables) + secondVariable.evaluateWithVariables(variables);
    }
}
