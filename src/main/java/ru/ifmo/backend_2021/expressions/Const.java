package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Const extends Operand {
    private final int value;
    public Const(int constValue) {
        this.value = constValue;
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
        return this.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Const && this.value == ((Const) o).value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return value;
    }
}
