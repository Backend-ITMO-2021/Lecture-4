package ru.ifmo.backend_2021.expressions;

import java.util.Set;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public class Multiply extends BinaryOperator {
    public Multiply(Expression left, Expression right) {
        super(left, right, MUL_SYMBOL, MUL_PRIORITY);
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) * right.evaluate(x);
    }

    @Override
    public boolean equals(Expression expression) {
        if (expression instanceof Multiply){
            return (this.left.equals(((Multiply) expression).right) && this.right.equals(((Multiply) expression).left)) ||
                    (this.left.equals(((Multiply) expression).left) && this.right.equals(((Multiply) expression).right));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Set.of(left, "*", right).hashCode();
    }
}
