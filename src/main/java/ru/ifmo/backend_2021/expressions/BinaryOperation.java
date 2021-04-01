package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public abstract class BinaryOperation implements Expression {
    private Expression first, second;
    String flag;

    public BinaryOperation(Expression first, Expression second, String flag) {
        this.first = first;
        this.second = second;
        this.flag = flag;
    }

    abstract int operation(int first, int second);

    public int evaluate(int x) {
        return operation(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + first.toString() + " " + flag + " " + second.toString() + ")";
    }


    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();

        if (p(first) > p(this) && !(first instanceof Variable || first instanceof Const)) {
            sb.append("(").append(first.toMiniString()).append(")");
        } else {
            sb.append(first.toMiniString());
        }
        sb.append(" ").append(flag).append(" ");

        if (second instanceof Variable || second instanceof Const) {
            sb.append(second.toMiniString());
        } else {
            if ((p(second) > p(this))) {
                sb.append("(").append(second.toMiniString()).append(")");
            } else if ((p(second) != p(this))) {
                sb.append(second.toMiniString());
            } else {
                if ((second instanceof BinaryOperation) && !flag.equals(((BinaryOperation) second).flag)) {
                    sb.append("(").append(second.toMiniString()).append(")");
                } else if (flag.equals("/") || flag.equals("-")) {
                    sb.append("(").append(second.toMiniString()).append(")");
                } else {
                    sb.append(second.toMiniString());
                }
            }
        }

        return sb.toString();
    }

    int p(Expression e) {
        return Expression.getPriority(e);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, flag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryOperation that = (BinaryOperation) o;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second) && Objects.equals(flag, that.flag);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return operation(first.evaluateWithVariables(variables), second.evaluateWithVariables(variables));
    }
}
