package ru.ifmo.backend_2021.expressions;

public class Subtract extends BinOp {

    public Subtract(Expression curVar1, Expression curVar2) {
        super(curVar1, curVar2, "-", 2);
    }

    @Override
    public int solve(int num1, int num2) {
        return num1 - num2;
    }
}
