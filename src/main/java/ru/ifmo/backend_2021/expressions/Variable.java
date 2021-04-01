package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Variable implements Expression{
    private String varName;

    public Variable(String varName) {
        this.varName = varName;
    }

    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return variables.getOrDefault(varName, 0);
    }

    @Override
    public String toString() {
        return varName;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Variable) return this.varName.equals(((Variable) o).varName);
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(varName);
    }
}
