package ru.ifmo.backend_2021.expressions;

public abstract class Operation extends Expression {
    public Operation(int EXPRESSION_PRIORITY) {
        this.EXPRESSION_PRIORITY = EXPRESSION_PRIORITY;
    }
}
