package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Variable extends Expression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toMiniString() {
        return variable;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (char c : variable.toCharArray()) {
            hashCode += c;
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Variable && variable.equals(((Variable) obj).variable);
    }

    @Override
    public String toString() {
        return String.valueOf(variable);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return evaluate(variables.get(variable));
    }
}
