package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public abstract class BinaryExpression extends Expression{
    protected Expression val1;
    protected Expression val2;
    private final char delimiter;

    public BinaryExpression(Expression val1, Expression val2, char delimiter){
        this.val1 = val1;
        this.val2 = val2;
        this.delimiter = delimiter;

    }

    @Override
    public int hashCode() {
        return val1.hashCode() + delimiter + val2.hashCode();
    }

    @Override

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        return val1.equals(((BinaryExpression) obj).val1) && val2.equals(((BinaryExpression) obj).val2);
    }

    @Override
    public String toMiniString() {
        return (this.val1.toMiniString() + " " + this.delimiter + " " + this.val2.toMiniString());
    }

    @Override
    public String toString() {
        return ("(" + this.val1.toString() + " " + this.delimiter + " " + this.val2.toString() + ")");
    }
}
