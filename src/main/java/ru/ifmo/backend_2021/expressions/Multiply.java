package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

public class Multiply extends BinaryOperator{

    public Multiply(Expression left, Expression right) {
        super(left, right, "*", 2);
    }

    @Override
    public int evaluate(int x) {
        return this.left.evaluate(x) * this.right.evaluate(x);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Multiply)
            return (this.left.equals(((Multiply) object).left) && this.right.equals(((Multiply) object).right)) ||
                    (this.left.equals(((Multiply) object).right) && this.right.equals(((Multiply) object).left));
        else return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(left, right, symbol);
    }
}

