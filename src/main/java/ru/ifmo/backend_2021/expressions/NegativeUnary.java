package ru.ifmo.backend_2021.expressions;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public class NegativeUnary extends UnaryOperator {
    public NegativeUnary(Expression expression) {
        super(expression, UNARY_NEG_SYMBOL, UNARY_NEG_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return -1 * expression.evaluate(x);
    }
}
