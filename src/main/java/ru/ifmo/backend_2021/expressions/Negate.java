package ru.ifmo.backend_2021.expressions;

public class Negate extends Expression {
    private final Expression value;

    public Negate(Expression value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return -value.evaluate(x);
    }

    @Override
    public String toString() {
        return "-" + value.toString();
    }

    @Override
    public String toMiniString() {
        return "-" + value.toMiniString();
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
