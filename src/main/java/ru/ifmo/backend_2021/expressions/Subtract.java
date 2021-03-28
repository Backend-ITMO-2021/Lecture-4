package ru.ifmo.backend_2021.expressions;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public class Subtract extends BinaryOperator {
    public Subtract(Expression left, Expression right) {
        super(left, right, SUB_SYMBOL, SUB_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) - right.evaluate(x);
    }
}
