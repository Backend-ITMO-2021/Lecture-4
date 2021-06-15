package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

public abstract class BinaryExpression implements Expression {
    public final boolean isSymmetrical;
    protected final Expression first, second;
    private final char operator;

    public BinaryExpression(Expression first, Expression second, char operator, boolean isSymmetrical) {
        this.isSymmetrical = isSymmetrical;
        this.first = first;
        this.second = second;
        this.operator = operator;
    }

    public String toMiniString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(first.toMiniString());
        if (first instanceof BinaryExpression && first.getPriority() > this.getPriority()) {
            sb.insert(0, "(");
            sb.append(")");
        }
        sb.append(" ");
        sb.append(this.operator);
        sb.append(" ");
        if (second instanceof BinaryExpression &&
                (second.getPriority() > this.getPriority() ||
                        ((!isSymmetrical || !((BinaryExpression) second).isSymmetrical)&&
                        second.getPriority() == this.getPriority()))) {
            sb.append("(");
            sb.append(second.toMiniString());
            sb.append(")");
        } else {
            sb.append(second.toMiniString());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("(");
        sb.append(first.toString());
        sb.append(" ");
        sb.append(this.operator);
        sb.append(" ");
        sb.append(second.toString());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            BinaryExpression other = (BinaryExpression) o;
            return first.equals(other.first) && second.equals(other.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, operator, isSymmetrical);
    }
}
