package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

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
    public String toMiniString() {
        return Integer.toString(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object comparable) {
        if (comparable instanceof Const){
            int new_value = ((Const) comparable).value;
            boolean result = value == new_value;
            return result;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}