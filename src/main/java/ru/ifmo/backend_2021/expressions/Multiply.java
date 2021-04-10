package ru.ifmo.backend_2021.expressions;

public class Multiply extends BinaryOperator {

    public Multiply(Expression a, Expression b) {
        super(String.valueOf(Utils.MUL), a, b);
    }

    @Override
    int evaluate(int a, int b) {
        return a * b;
    }

    @Override
    public String toMiniString() {
        String left = leftExpression.toMiniString();
        String right = rightExpression.toMiniString();

        if (leftExpression instanceof Add || leftExpression instanceof Subtract) {
            left = Utils.LEFT_B + left + Utils.RIGHT_B;
        }

        if (rightExpression instanceof Add || rightExpression instanceof Subtract || rightExpression instanceof Divide) {
            right = Utils.LEFT_B + right + Utils.RIGHT_B;
        }

        return left + " " + mark + " " + right;
    }
}
