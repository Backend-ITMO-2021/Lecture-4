package ru.ifmo.backend_2021.expressions;

public abstract class BinaryOperation implements Expression {
    private Expression left, right;

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int evaluate(int x) {
        return this.calculate(this.left.evaluate(x), this.right.evaluate(x));
    }

    public String toMiniString() {
        return "";
    }

    abstract int calculate(int left, int right);
}
