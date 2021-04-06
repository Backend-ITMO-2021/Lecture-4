package ru.ifmo.backend_2021.expressions;

public class Subtract extends BinaryOperator {
    public Subtract(Expression first, Expression second) {
        super(first, second, "-");
    }
}
