package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public class Subtract extends BinaryOperator {
    public Subtract(Expression left, Expression right) {
        super(left, right, SUB_SYMBOL, SUB_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) - right.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return left.evaluateWithVariables(variables) - right.evaluateWithVariables(variables);
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof Subtract){
            return (this.left.equals(((Subtract) expression).left) && this.right.equals(((Subtract) expression).right));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, "-", right);
    }
}
