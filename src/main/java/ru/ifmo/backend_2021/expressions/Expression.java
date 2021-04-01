package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public interface Expression {
    int evaluate(int x);

    String toMiniString();

    int evaluateWithVariables(Map<String, Integer> variables);

    static int getPriority(Expression expression) {
        if (expression instanceof Negate) return 0;
        if (expression instanceof Multiply) return 1;
        if (expression instanceof Divide) return 1;
        if (expression instanceof Add) return 2;
        if (expression instanceof Subtract) return 2;
        return 3;
    }
}
