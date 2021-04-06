package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

public abstract class BinaryOperator extends Expression {

    protected Expression first;
    protected Expression second;

    public BinaryOperator(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    protected abstract int mathSolve(int x, int y);

    protected abstract String getOperationType();

    @Override
    public int evaluate(int x) {
        return mathSolve(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, getOperationType());
    }

    @Override
    public String toString() {
        return "(" + first + " " + getOperationType() + " " + second + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (first.getPriority() < this.getPriority()) {
            sb.append("(").append(first.toMiniString()).append(")");
        } else {
            sb.append(first.toMiniString());
        }

        sb.append(" ").append(getOperationType()).append(" ");
        if (requireBrackets(second)) {
            sb.append("(").append(second.toMiniString()).append(")");
        } else {
            sb.append(second.toMiniString());
        }
        return sb.toString();
    }

    private boolean requireBrackets(Expression second){
        return second.getPriority() < this.getPriority() ||
                second.getPriority() == this.getPriority() &&
                        (second.isImportant() || this.isImportant());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof BinaryOperator) {
            BinaryOperator equalizer = (BinaryOperator) object;
            return Objects.equals(first, equalizer.first) && Objects.equals(second, equalizer.second)
                    && Objects.equals(getOperationType(), equalizer.getOperationType());
        } else {
            return false;
        }
    }
}
