package ru.ifmo.backend_2021.expressions;

public class Variable implements Expression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public int evaluate(int x) {
        return x;
    }

    public String toString() {
        return name;
    }

    public String toMiniString() {
        return name;
    }
}
