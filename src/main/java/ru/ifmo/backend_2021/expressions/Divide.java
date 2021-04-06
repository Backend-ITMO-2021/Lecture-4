package ru.ifmo.backend_2021.expressions;

public class Divide extends BinaryOperator {
    public Divide(Expression first, Expression second) {
        super(first, second, "/");
    }
}
