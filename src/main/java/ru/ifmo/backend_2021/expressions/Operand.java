package ru.ifmo.backend_2021.expressions;

public abstract class Operand extends Expression {
    public Operand() {
        this.EXPRESSION_PRIORITY = 0;
    }

    @Override
    public abstract int hashCode();
}
