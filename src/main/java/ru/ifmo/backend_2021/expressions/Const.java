package ru.ifmo.backend_2021.expressions;

public class Const implements Expression {
    private int val;

    public Const(int val) {
        this.val = val;
    }

    public int evaluate(int x) {
        return this.val;
    }

    public String toString() {
        return String.valueOf(this.val);
    }

    public String toMiniString() {
        return String.valueOf(this.val);
    }
}