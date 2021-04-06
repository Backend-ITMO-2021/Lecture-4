package ru.ifmo.backend_2021.expressions;

public class Const extends Expression {
    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public String toMiniString() {
        return String.valueOf(value);
    }

    @Override
    protected int getPriority() {
        return 5;
    }

    @Override
    protected boolean isImportant() {
        return false;
    }
}
