package ru.ifmo.backend_2021.expressions;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public abstract class BinaryOperator extends Expression implements Operator {
    protected Expression left;
    protected Expression right;
    protected String symbol;

    BinaryOperator(Expression left, Expression right, String symbol, int priority) {
        super(priority);
        this.left = left;
        this.right = right;
        this.symbol = symbol;
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();

        if (left.priority > this.priority && !(left instanceof Operand)) {
            sb.append(surroundWithBrackets(left.toMiniString()));
        } else {
            sb.append(left.toMiniString());
        }
        sb.append(" ");
        sb.append(symbol);
        sb.append(" ");
        if (!(right instanceof Operand) && (right.priority > this.priority ||
                (right.priority == this.priority && ((symbol.equals(SUB_SYMBOL) || symbol.equals(DIV_SYMBOL))
                        || ((right instanceof BinaryOperator) && !symbol.equals(((BinaryOperator) right).symbol)))))) {
            sb.append(surroundWithBrackets(right.toMiniString()));
        } else {
            sb.append(right.toMiniString());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return surroundWithBrackets(left.toString() + " " + symbol + " " + right.toString());
    }
}
