package ru.ifmo.backend_2021.expressions;

public class Subtract extends BinaryOperator {

    public Subtract(Expression a, Expression b) {
        super(String.valueOf(Utils.SUB), a, b);
    }

    @Override
    int evaluate(int a, int b) {
        return a - b;
    }

    @Override
    public String toMiniString() {
        String right = rightExpression.toMiniString();

        if (rightExpression instanceof Subtract || rightExpression instanceof Add) {
            right = Utils.LEFT_B + right + Utils.RIGHT_B;
        }

        return leftExpression.toMiniString() + " " + mark + " " + right;
    }
}
