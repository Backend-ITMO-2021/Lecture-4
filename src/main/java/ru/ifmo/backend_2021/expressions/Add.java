package ru.ifmo.backend_2021.expressions;

public class Add extends BinaryOperation {
    public Add(Expression first, Expression second) {
        super(first, second, "+");
    }

    public int operation(int first, int second) {
        return first + second;
    }

}
