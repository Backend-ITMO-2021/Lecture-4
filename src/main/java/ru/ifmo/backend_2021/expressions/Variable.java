package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

public class Variable extends Expression{

    private final String varValue;
    public Variable(String value) {
        this.varValue = value;
        this.SYMBOL = "";
        this.PRIORITY = 0;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return varValue;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable)) return false;
        Variable variable = (Variable) o;
        return Objects.equals(varValue, variable.varValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(varValue);
    }
}
