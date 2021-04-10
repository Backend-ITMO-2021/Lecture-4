package ru.ifmo.backend_2021.expressions;

public class Variable extends Expression{
    private final String name;

    public Variable(String name){
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Variable && ((Variable) obj).name.equals(name);
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
