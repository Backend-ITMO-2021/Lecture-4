package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public abstract class BinaryOperation implements Expression {
    private Expression left, right;
    private String operation;

    public BinaryOperation(Expression left, Expression right, String operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    public int evaluate(int x) {
        return this.calculate(this.left.evaluate(x), this.right.evaluate(x));
    }

    public int evaluateWithVariables(Map<String, Integer> variables) {
        return this.calculate(this.left.evaluateWithVariables(variables), this.right.evaluateWithVariables(variables));
    }

    public String toMiniString() {
        return this.toMiniString(0, "", false);
    }

    public String toMiniString(int parentPriority, String parentOperation, boolean atRight) {
        String format = "%s %s %s";
        if (
            (parentPriority > Expression.priority(this))
            || (atRight && (parentOperation.equals(this.operation) && (this.operation.equals("/") || this.operation.equals("-"))))
            || (atRight && (parentOperation.equals("-") && (this.operation.equals("-") || this.operation.equals("+"))))
            || (parentOperation.equals("/") && !this.operation.equals("/"))
            || (atRight && parentOperation.equals("*") && this.operation.equals("/"))
        ) {
            format = "(" + format + ")";
        }

        return String.format(format,
                this.left.toMiniString(Expression.priority(this), this.operation, false),
                this.operation,
                this.right.toMiniString(Expression.priority(this), this.operation, true));
    }

    public String toString() {
        return String.format("(%s %s %s)", this.left.toString(), this.operation, this.right.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BinaryOperation) {
            return this.equalsOperation((BinaryOperation) o);
        }

        return false;
    }

    private Boolean equalsOperation(BinaryOperation expression) {
        return this.left.equals(expression.left)
                && this.right.equals(expression.right)
                && this.operation.equals(expression.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.left, this.right, this.operation);
    }

    abstract int calculate(int left, int right);
}
