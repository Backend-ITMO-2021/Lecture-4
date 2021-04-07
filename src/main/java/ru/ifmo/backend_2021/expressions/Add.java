package ru.ifmo.backend_2021.expressions;

public class Add extends Operations {

    public Add(Expression var1, Expression var2) {
        super("+", var1, var2);
    }

    @Override
    int evaluate(int var1, int var2) {
        return var1 + var2;
    }
}