package ru.ifmo.backend_2021.expressions;

import java.util.Map;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public class NegativeUnary extends UnaryOperator {
    public NegativeUnary(Expression expression) {
        super(expression, UNARY_NEG_SYMBOL, UNARY_NEG_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return -1 * expression.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return -1 * expression.evaluateWithVariables(variables);
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof NegativeUnary){
            return this.expression.equals(((NegativeUnary) expression).expression);
        }
        return false;
    }
}
