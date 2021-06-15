package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Add extends BinaryExpression {
    public Add(Expression first, Expression second) {
        super(first, second, '+', true);
    }

    @Override
    public int evaluate(int x) {
        return this.first.evaluate(x) + this.second.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return this.first.evaluateWithVariables(variables) + this.second.evaluateWithVariables(variables);
    }

    @Override
    public int getPriority() {
        return 2;
    }
}