package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Variable extends Expression {

    String valueName;

    public Variable(String name) {
        this.valueName = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return variables.get(valueName);
    }

    @Override
    public String toMiniString() {
        return valueName;
    }

    @Override
    public String toString() {
        return valueName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variable variable = (Variable) o;
        return Objects.equals(valueName, variable.valueName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueName);
    }
}
