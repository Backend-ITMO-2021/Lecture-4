package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public abstract class Expression {
    protected int priority;

    Expression(int priority) {
        this.priority = priority;
    }

    public abstract int evaluate(int x);

    public abstract int evaluateWithVariables(Map<String, Integer> variables);

    public abstract String toMiniString();

    protected static String surroundWithBrackets(String value) {
        return "(" + value + ")";
    }

    public abstract boolean equals(Expression expression);

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Expression){
            return this.equals((Expression) obj);
        }
        return false;
    }
}
