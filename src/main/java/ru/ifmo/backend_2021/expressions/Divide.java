package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public class Divide extends BinaryOperator {
    public Divide(Expression left, Expression right) {
        super(left, right, DIV_SYMBOL, DIV_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) / right.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return left.evaluateWithVariables(variables) / right.evaluateWithVariables(variables);
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof Divide){
            return (this.left.equals(((Divide) expression).left) && this.right.equals(((Divide) expression).right));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, "/", right);
    }
}
