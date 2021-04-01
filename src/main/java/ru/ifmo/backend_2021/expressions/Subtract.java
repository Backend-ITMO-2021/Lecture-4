package ru.ifmo.backend_2021.expressions;

public class Subtract extends BinaryOperation{
    public Subtract(Expression first, Expression second) {
        super(first, second, "-");
    }

    public int operation(int first, int second) {
        return first - second;
    }

}
