package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public abstract class BinaryOperation extends Operation {
    Expression left;
    Expression right;
    String OPERATION_SYMBOL;
    BinaryOperation(Expression in_left, Expression in_right, String IN_OPERATION_SYMBOL, int IN_OPERATION_PRIORITY) {
        super(IN_OPERATION_PRIORITY);
        this.left = in_left;
        this.right = in_right;
        this.OPERATION_SYMBOL = IN_OPERATION_SYMBOL;
    }

    @Override
    public String toString() {
        return "(" + this.left.toString() + " " + OPERATION_SYMBOL + " " + this.right.toString() + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (this.left.EXPRESSION_PRIORITY < this.EXPRESSION_PRIORITY && !(this.left instanceof Operand)) {
            sb.append("(").append(this.left.toMiniString()).append(")");
        } else {
            sb.append(this.left.toMiniString());
        }
        sb.append(" ").append(this.OPERATION_SYMBOL).append(" ");
        if (this.right.EXPRESSION_PRIORITY <= this.EXPRESSION_PRIORITY && this.right instanceof BinaryOperation
        && !this.OPERATION_SYMBOL.equals("+")
        && (!this.OPERATION_SYMBOL.equals("*") || !((BinaryOperation) this.right).OPERATION_SYMBOL.equals("*"))) {
            sb.append("(").append(this.right.toMiniString()).append(")");
        } else {
            sb.append(this.right.toMiniString());
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BinaryOperation && this.OPERATION_SYMBOL.equals(((BinaryOperation) o).OPERATION_SYMBOL)
                && this.left.equals(((BinaryOperation) o).left) && this.right.equals(((BinaryOperation) o).right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, OPERATION_SYMBOL);
    }
}
