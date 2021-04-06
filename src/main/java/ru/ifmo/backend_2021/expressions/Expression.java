package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public abstract class Expression {
    public abstract int evaluate(int x);

    public int evaluateWithVariables(Map<String, Integer> variables) {
        return -1;
    }

    public abstract String toString();

    public abstract String toMiniString();

    protected abstract int getPriority();

    protected abstract boolean isImportant();
}
