package ru.ifmo.backend_2021.expressions;

import java.security.KeyException;
import java.util.Map;

public class Variable extends UnaryExpression{
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return variables.getOrDefault(this.name, 0);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Variable) {
            return name.equals(((Variable) o).name);
        }
        return false;
    }
}
