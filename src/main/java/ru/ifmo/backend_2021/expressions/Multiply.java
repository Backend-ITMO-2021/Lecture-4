package ru.ifmo.backend_2021.expressions;

public class Multiply extends BinaryOperator {
    public Multiply(Expression first, Expression second) {
        super(first, second, "*");
    }
}
