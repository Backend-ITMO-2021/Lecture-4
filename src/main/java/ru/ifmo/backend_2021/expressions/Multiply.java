package ru.ifmo.backend_2021.expressions;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public class Multiply extends BinaryOperator {
    public Multiply(Expression left, Expression right) {
        super(left, right, MUL_SYMBOL, MUL_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) * right.evaluate(x);
    }
}
