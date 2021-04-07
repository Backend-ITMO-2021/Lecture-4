package ru.ifmo.backend_2021.expressions;

public class Subtract extends Operations {

    public Subtract(Expression var1, Expression var2) {
        super("-", var1, var2);
    }

    @Override
    int evaluate(int var1, int var2) {
        return var1 - var2;
    }

    @Override
    public String toMiniString() {
        String left = var1.toMiniString();
        String right = var2.toMiniString();

        if (var2 instanceof Subtract || var2 instanceof Add) {
            right = "(" + right + ")";
        }

        return left + " " + sign + " " + right;
    }
}