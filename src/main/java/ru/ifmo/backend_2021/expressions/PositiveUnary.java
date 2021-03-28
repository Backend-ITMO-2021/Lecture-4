package ru.ifmo.backend_2021.expressions;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.UNARY_POS_PRIORITY;
import static ru.ifmo.backend_2021.expressions.ExpressionConstants.UNARY_POS_SYMBOL;

public class PositiveUnary extends UnaryOperator {
    public PositiveUnary(Expression expression) {
        super(expression, UNARY_POS_SYMBOL, UNARY_POS_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return expression.evaluate(x);
    }
}
