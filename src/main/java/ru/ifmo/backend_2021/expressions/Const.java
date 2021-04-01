package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Const implements Expression {
    private Integer value;

    public Const(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public int evaluate(int x) {
        return value;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Const) return this.value.equals(((Const) o).value);
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
