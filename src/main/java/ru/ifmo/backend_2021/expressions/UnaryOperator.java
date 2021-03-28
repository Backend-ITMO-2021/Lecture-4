package ru.ifmo.backend_2021.expressions;

public class UnaryOperator extends Expression {
    protected Expression expression;
    protected String symbol;

    public UnaryOperator(Expression expression, String symbol, int priority) {
        super(priority);
        this.expression = expression;
        this.symbol = symbol;
    }

    @Override
    public String toMiniString() {
        if (expression instanceof Operand)
            return symbol + expression.toMiniString();
        return symbol + surroundWithBrackets(expression.toMiniString());
    }

    @Override
    public String toString() {
        if (expression instanceof Operand)
            return symbol + expression.toString();
        return symbol + surroundWithBrackets(expression.toString());
    }
}
