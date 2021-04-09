package ru.ifmo.backend_2021.expressions;

public class Add extends BinOp {

    public Add(Expression curVar1, Expression curVar2) {
        super(curVar1, curVar2, "+", 2);
    }

    @Override
    public int solve(int num1, int num2) {
        return num1 + num2;
    }

}
