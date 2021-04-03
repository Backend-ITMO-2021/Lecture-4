package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public abstract class BinaryOperator extends Expression {

    Expression a, b;
    String op;

    public BinaryOperator(String op, Expression a, Expression b) {
        this.op = op;
        this.a = a;
        this.b = b;
    }

    abstract int evaluate(int a, int b);

    @Override
    public int evaluate(int x) {
        return evaluate(a.evaluate(x), b.evaluate(x));
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return evaluate(a.evaluateWithVariables(variables), b.evaluateWithVariables(variables));
    }

    @Override
    public String toMiniString() {
        return a.toMiniString() + " " + op + " " + b.toMiniString();
    }

    @Override
    public String toString() {
        return "(" + a.toString() + " " + op + " " + b.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryOperator that = (BinaryOperator) o;
        return Objects.equals(a, that.a) && Objects.equals(b, that.b) && Objects.equals(op, that.op);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, op);
    }
}
