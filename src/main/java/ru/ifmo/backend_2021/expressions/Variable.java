package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Variable extends Expression {
    private final String name;

    public Variable(String curName) {
        name = curName;
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

        return variables.getOrDefault(name, 0);
    }

    @Override
    public String toString() {
        return name;
    }

}
