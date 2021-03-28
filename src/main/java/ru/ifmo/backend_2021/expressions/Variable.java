package ru.ifmo.backend_2021.expressions;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.OPERAND_PRIORITY;

public class Variable extends Expression implements Operand{
    private int value;
    private String name;

    public Variable(String name) {
        super(OPERAND_PRIORITY);
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof Variable){
            return this.name.equals(((Variable) expression).name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
