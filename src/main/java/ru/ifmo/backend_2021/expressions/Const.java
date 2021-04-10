package ru.ifmo.backend_2021.expressions;

public class Const extends Expression{
    private int val;

    public Const(int val){
        this.val = val;
    }

    @Override
    public int hashCode() {
        return val*999;
    }

    @Override
    public int evaluate(int x) {
        return this.val;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Const && ((Const) obj).val == val;
    }

    @Override
    public String toMiniString() {
        return String.valueOf(this.val);
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }
}
