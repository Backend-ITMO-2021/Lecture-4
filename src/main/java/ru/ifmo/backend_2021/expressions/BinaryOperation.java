package ru.ifmo.backend_2021.expressions;

public abstract class BinaryOperation implements Expression {
    private Expression left, right;
    private String operation;

    public BinaryOperation(Expression left, Expression right, String operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    public int evaluate(int x) {
        return this.calculate(this.left.evaluate(x), this.right.evaluate(x));
    }

    public String toMiniString() {
        return "";
    }

    public String toString() {
        return String.format("(%s %s %s)", this.left.toString(), this.operation, this.right.toString());
    }

    abstract int calculate(int left, int right);
}
