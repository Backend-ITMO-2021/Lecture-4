package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Add extends BinaryOperation {
    public Add(Expression in_left, Expression in_right) {
        super(in_left, in_right, "+", 1);
    }

    @Override
    public int evaluate(int x) {
        return this.left.evaluate(x) + this.right.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return this.left.evaluateWithVariables(variables) + this.right.evaluateWithVariables(variables);
    }
}
