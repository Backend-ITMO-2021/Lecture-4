package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Set;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.ADD_PRIORITY;
import static ru.ifmo.backend_2021.expressions.ExpressionConstants.ADD_SYMBOL;

public class Add extends BinaryOperator {

    public Add(Expression left, Expression right) {
        super(left, right, ADD_SYMBOL, ADD_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) + right.evaluate(x);
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return left.evaluateWithVariables(variables) + right.evaluateWithVariables(variables);
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof Add){
            return (this.left.equals(((Add) expression).right) && this.right.equals(((Add) expression).left)) ||
                    (this.left.equals(((Add) expression).left) && this.right.equals(((Add) expression).right));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Set.of(left, "+", right).hashCode();
    }
}
