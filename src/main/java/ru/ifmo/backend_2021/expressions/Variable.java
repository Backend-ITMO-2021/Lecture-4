package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Variable extends Expression {

    String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return variables.get(name);
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object comparable) {
        if (comparable == null || getClass() != comparable.getClass()){
            return false;
        }
        Variable var = (Variable) comparable;
        return Objects.equals(name, var.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}