package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Variable extends Operand {
    private final String name;

    public Variable(String in_name) {
        this.name = in_name;
    }


    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Variable && this.name.equals(((Variable) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return variables.get(this.name);
    }
}
