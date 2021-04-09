package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Const extends Expression {
    private final Integer var;

    public Const(Integer curVar) {
        var = curVar;
    }

    public int evaluate(int x) {
        return var;
    }

    @Override
    public String toMiniString() {
        return var.toString();
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return var;
    }

    @Override
    public String toString() {
        return var.toString();
    }



}
