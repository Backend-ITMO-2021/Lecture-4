package ru.ifmo.backend_2021.expressions;

public class Add extends BinaryOperator {
    public Add(Expression first, Expression second) {
        super(first, second, "+");
    }
}
