package ru.ifmo.backend_2021.expressions;


public class Const extends Expression implements Operand {
    int val;

    public Const(int val) {
        super(3);
        this.val = val;
    }

    @Override
    public int evaluate(int x) {
        return val;
    }
    
    @Override
    public String toMiniString() {
        return Integer.toString(val);
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof Const) {
            return this.toString().equals(expression.toString()); 
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.toString(val).hashCode();
    }
}
