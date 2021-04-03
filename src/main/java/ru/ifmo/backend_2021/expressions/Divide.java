package ru.ifmo.backend_2021.expressions;

public class Divide extends BinaryOperator {

    public Divide(Expression a, Expression b) {
        super("/", a, b);
    }

    @Override
    int evaluate(int a, int b) {
        return a / b;
    }

    @Override
    public String toMiniString() {
        String left = a.toMiniString();
        String right = b.toMiniString();

        if (a instanceof Add || a instanceof Subtract) {
            left = "(" + left + ")";
        }

        if (b instanceof Add || b instanceof Subtract || b instanceof Divide || b instanceof Multiply) {
            right = "(" + right + ")";
        }

        return left + " " + op + " " + right;
    }
}
