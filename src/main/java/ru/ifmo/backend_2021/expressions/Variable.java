package ru.ifmo.backend_2021.expressions;

public class Variable extends Expression {

    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String toMiniString() {
        return value;
    }

    @Override
    protected int getPriority() {
        return 10;
    }

    @Override
    protected boolean isImportant() {
        return false;
    }
}
