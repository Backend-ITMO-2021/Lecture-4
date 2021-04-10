package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public abstract class BinaryOperator extends Expression {

    Expression leftExpression, rightExpression;
    String mark;

    public BinaryOperator(String mark, Expression leftExpression, Expression rightExpression) {
        this.mark = mark;
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    abstract int evaluate(int a, int b);

    @Override
    public int evaluate(int x) {
        return evaluate(leftExpression.evaluate(x), rightExpression.evaluate(x));
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return evaluate(leftExpression.evaluateWithVariables(variables), rightExpression.evaluateWithVariables(variables));
    }

    @Override
    public String toMiniString() {
        return leftExpression.toMiniString() + " " + mark + " " + rightExpression.toMiniString();
    }

    @Override
    public String toString() {
        return Utils.LEFT_B + leftExpression.toString() + " " + mark + " " + rightExpression.toString() + Utils.RIGHT_B;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryOperator that = (BinaryOperator) o;
        return Objects.equals(leftExpression, that.leftExpression) && Objects.equals(rightExpression, that.rightExpression) && Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, rightExpression, mark);
    }
}
