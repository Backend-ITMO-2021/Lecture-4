package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Const extends UnaryExpression {
    private final Integer value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        return this.value;
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Const) {
            return value.equals(((Const) o).value);
        }
        return false;
    }
}
