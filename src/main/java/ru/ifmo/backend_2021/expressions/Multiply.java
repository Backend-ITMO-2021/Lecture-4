package ru.ifmo.backend_2021.expressions;

public class Multiply extends BinaryOperation{
    public Multiply(Expression first, Expression second) {
        super(first, second, "*");
    }

    public int operation(int first, int second) {
        return first * second;
    }

}
