package ru.ifmo.backend_2021.expressions;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.ADD_PRIORITY;
import static ru.ifmo.backend_2021.expressions.ExpressionConstants.ADD_SYMBOL;

public class Add extends BinaryOperator {

    public Add(Expression left, Expression right) {
        super(left, right, ADD_SYMBOL, ADD_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) + right.evaluate(x);
    }
}
