package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Const extends Expression {
    private int value;

    public Const(int value) {
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
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Const && value == ((Const) obj).value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return value;
    }
}
