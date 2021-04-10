
package ru.ifmo.backend_2021.expressions;

public class Subtract extends Operations {

    public Subtract(Expression first, Expression second) {
        super(first, second,"-");
    }

    @Override
    int evaluate(int first, int second) {
        return first - second;
    }

    @Override
    public String toMiniString() {
        String left = first.toMiniString();
        String right = second.toMiniString();

        if (second instanceof Subtract || second instanceof Add) {
            right = "(" + right + ")";
        }

        return left + " " + ch + " " + right;
    }
}