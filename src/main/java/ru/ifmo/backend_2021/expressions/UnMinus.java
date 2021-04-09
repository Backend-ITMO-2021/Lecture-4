package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class UnMinus extends Expression{

    private final Expression var;

    public UnMinus(Expression curVar) {
        var = curVar;
    }

    @Override
    public int evaluate(int x) {
        return -var.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        final int i = -var.evaluateWithVariables(variables);
        return i;
    }


}
