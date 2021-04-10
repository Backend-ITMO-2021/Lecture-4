package ru.ifmo.backend_2021.expressions;

public class Add extends Operations {

    public Add(Expression first, Expression second) {
        super(first, second, "+");
    }

    @Override
    int evaluate(int first, int second) {
        return first + second;
    }
}