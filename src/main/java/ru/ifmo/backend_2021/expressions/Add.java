package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

public class Add extends BinaryOperator{

    public Add(Expression left, Expression right) {
        super(left, right, "+", 1);
    }

    @Override
    public int evaluate(int x) {
        return this.left.evaluate(x) + this.right.evaluate(x);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Add)
            return (this.left.equals(((Add) object).left) && this.right.equals(((Add) object).right)) ||
                    (this.left.equals(((Add) object).right) && this.right.equals(((Add) object).left));
        else return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(left, right, symbol);
    }
}
