package ru.ifmo.backend_2021.expressions;

public class Divide extends Operations {

    public Divide(Expression first, Expression second) {
        super( first, second, "/");
    }

    @Override
    int evaluate(int first, int second) {
        return first / second;
    }

    @Override
    public String toMiniString() {
        String left = first.toMiniString();
        String right = second.toMiniString();

        if (first instanceof Add || first instanceof Subtract) {
            left = "(" + left + ")";
        }

        if (second instanceof Add || second instanceof Subtract || second instanceof Divide || second instanceof Multiply) {
            right = "(" + right + ")";
        }

        return left + " " + ch + " " + right;
    }
}