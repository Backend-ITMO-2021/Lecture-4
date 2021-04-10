package ru.ifmo.backend_2021.expressions;

import java.util.Map;

abstract class Operations extends Expression {

    Expression first, second;
    String ch;

    public Operations(Expression first, Expression second, String ch) {
        this.ch = ch;
        this.first = first;
        this.second = second;
    }

    abstract int evaluate(int first, int second);

    @Override
    public int evaluate(int x) {
        return evaluate(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return evaluate(first.evaluateWithVariables(variables), second.evaluateWithVariables(variables));
    }
    @Override
    public String toString() {
        String result = "(" + first.toString() + " " + ch + " " + second.toString() + ")";
        return result;
    }
    @Override
    public String toMiniString() {
        String result = first.toMiniString() + " " + ch + " " + second.toMiniString();
        return result;
    }


    @Override
    public boolean equals(Object comparable) {
        if (comparable == null || comparable.getClass() != this.getClass()) {
            return false;
        }
        Operations oper = (Operations) comparable;
        return first.equals(oper.first) && second.equals(oper.second);
    }

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode() + ch.hashCode();
    }
}