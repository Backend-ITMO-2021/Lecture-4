package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public abstract class Expression {
    protected int priority;

    Expression(int priority) {
        this.priority = priority;
    }

    public int evaluate(int x) {
        return 0;
    }

    public int evaluateWithVariables(Map<String, Integer> variables) {
        return 0;
    }

    public String toMiniString() {
        return "";

    }

    protected static String surroundWithBrackets(String value) {
        return "(" + value + ")";
    }

}
