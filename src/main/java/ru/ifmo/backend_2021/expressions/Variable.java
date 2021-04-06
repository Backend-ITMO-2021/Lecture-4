package ru.ifmo.backend_2021.expressions;

public class Variable extends Expression implements Operand {
    String name;

    public Variable(String name) {
        super(3);
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
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof Variable) {
            return this.name.equals(expression.toMiniString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
