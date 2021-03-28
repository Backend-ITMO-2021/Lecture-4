package ru.ifmo.backend_2021.expressions;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.OPERAND_PRIORITY;

public class Const extends Expression implements Operand{
    private int value;

    public Const(int value) {
        super(OPERAND_PRIORITY);
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public String toMiniString() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(value).hashCode();
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof Const){
            return this.value == (((Const) expression).value);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
