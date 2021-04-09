package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Variable extends Expression{
    protected String value;

    public Variable(String value) {
        super(0);
        this.value = value;
    }

    public String toString() {
        return value;
    }

    @Override
    public String toMiniString() {
        return value;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) { return variables.get(value);}

    @Override
    public boolean equals(Object object) {
        if (object instanceof Variable)
            return value.equals(((Variable) object).value);
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
