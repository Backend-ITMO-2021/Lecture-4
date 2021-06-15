package ru.ifmo.backend_2021.expressions;

public abstract class UnaryExpression implements Expression {
    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }
}
