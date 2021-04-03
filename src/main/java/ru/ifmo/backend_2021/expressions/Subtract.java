package ru.ifmo.backend_2021.expressions;

public class Subtract extends BinaryOperator {

    public Subtract(Expression a, Expression b) {
        super("-", a, b);
    }

    @Override
    int evaluate(int a, int b) {
        return a - b;
    }

    @Override
    public String toMiniString() {
        String right = b.toMiniString();

        if (b instanceof Subtract || b instanceof Add) {
            right = "(" + right + ")";
        }

        return a.toMiniString() + " " + op + " " + right;
    }
}
