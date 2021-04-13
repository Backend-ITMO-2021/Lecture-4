package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

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

    public String toMiniString(int parentPriority) {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Variable) {
            return this.name.equals(((Variable) o).name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
