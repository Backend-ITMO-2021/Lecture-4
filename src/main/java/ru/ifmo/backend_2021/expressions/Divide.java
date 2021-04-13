package ru.ifmo.backend_2021.expressions;

public class Divide extends BinaryOperation {
    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    public int calculate(int left, int right) {
        return left / right;
    }
}