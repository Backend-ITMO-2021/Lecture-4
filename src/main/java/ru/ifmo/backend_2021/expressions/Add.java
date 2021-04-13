package ru.ifmo.backend_2021.expressions;

public class Add extends BinaryOperation {
    public Add(Expression left, Expression right) {
        super(left, right, "+");
    }

    public int calculate(int left, int right) {
        return left + right;
    }
}