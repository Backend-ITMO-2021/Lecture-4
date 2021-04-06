package ru.ifmo.backend_2021.expressions;

public class Subtract extends BinaryOperator {
    public Subtract(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    protected int mathSolve(int x, int y) {
        return x - y;
    }

    @Override
    protected String getOperationType() {
        return "-";
    }

    @Override
    protected int getPriority() {
        return 1;
    }

    @Override
    protected boolean isImportant() {
        return true;
    }
}
