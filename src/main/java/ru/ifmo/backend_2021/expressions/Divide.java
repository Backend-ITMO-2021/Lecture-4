package ru.ifmo.backend_2021.expressions;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public class Divide extends BinaryOperator {
    public Divide(Expression left, Expression right) {
        super(left, right, DIV_SYMBOL, DIV_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) / right.evaluate(x);
    }
}
