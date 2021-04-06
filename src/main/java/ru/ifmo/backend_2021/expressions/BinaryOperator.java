package ru.ifmo.backend_2021.expressions;

import java.util.Objects;
import static ru.ifmo.backend_2021.BinaryPriorities.BINARY_PRIORITIES;

public class BinaryOperator extends Expression implements Operator {
    Expression first;
    Expression second;
    String operSymb;

    public BinaryOperator(Expression first, Expression second, String operSymb) {
        super(BINARY_PRIORITIES.get(operSymb));
        this.first = first;
        this.second = second;
        this.operSymb = operSymb;
    }

    protected int operation(int first, int second) {
        switch (operSymb) {
            case "+": return first + second;
            case "*": return first * second;
            case "-": return first - second;
            case "/": return first / second;
        }
        return -1;
    }

    @Override
    public int evaluate(int x) {
        return operation(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + first.toString() + " " + operSymb + " " + second.toString() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, operSymb);
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof BinaryOperator) {
            if (((BinaryOperator) expression).operSymb == this.operSymb) {
                if (this.operSymb == "+" || this.operSymb == "*") {
                    return (this.first.equals(((BinaryOperator) expression).second) && this.second.equals(((BinaryOperator) expression).first)) ||
                        (this.first.equals(((BinaryOperator) expression).first) && this.second.equals(((BinaryOperator) expression).second));
                } else {
                    return this.first.equals(((BinaryOperator) expression).first) && this.second.equals(((BinaryOperator) expression).second);
                }
            }
        }
        return false;
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();

        if (first.priority > this.priority && first instanceof Operator) {
            sb.append("(").append(first.toMiniString()).append(")");
        } else {
            sb.append(first.toMiniString());
        }
        sb.append(" ");
        sb.append(operSymb);
        sb.append(" ");
        if (second instanceof Operator && (second.priority > this.priority ||
                (second.priority == this.priority && ((operSymb == "-" || operSymb == "/")
                        || ((second instanceof BinaryOperator) && !operSymb.equals(((BinaryOperator) second).operSymb)))))) {
            sb.append("(").append(second.toMiniString()).append(")");
        } else {
            sb.append(second.toMiniString());
        }
        return sb.toString();
    }
}
