package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Const implements Expression {
    protected int val;

    public Const(int val) {
        this.val = val;
    }

    public int evaluate(int x) {
        return this.val;
    }

    public int evaluateWithVariables(Map<String, Integer> variables) {
        return this.val;
    }

    public String toString() {
        return String.valueOf(this.val);
    }

    public String toMiniString() {
        return this.toString();
    }

    public String toMiniString(int parentPriority, String parentOperation, boolean atRight) {
        return this.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Const) {
            return this.val == ((Const) o).val;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.val);
    }
}