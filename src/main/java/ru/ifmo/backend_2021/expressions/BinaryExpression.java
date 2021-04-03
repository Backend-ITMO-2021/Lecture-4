package ru.ifmo.backend_2021.expressions;

abstract class BinaryExpression extends Expression {
    protected final Expression firstVariable;
    protected final Expression secondVariable;
    protected final char sign;

    BinaryExpression(Expression v1, Expression v2, char sign) {
        this.firstVariable = v1;
        this.secondVariable = v2;
        this.sign = sign;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        BinaryExpression expr = (BinaryExpression) obj;
        return firstVariable.equals(expr.firstVariable) && secondVariable.equals(expr.secondVariable);
    }

    @Override
    public int hashCode() {
        return firstVariable.hashCode() + secondVariable.hashCode() + sign;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", firstVariable, sign, secondVariable);
    }

    @Override
    public String toMiniString() {
        return String.format("%s %s %s", firstVariable.toMiniString(), sign, secondVariable.toMiniString());
    }
}
