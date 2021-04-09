package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

public class Subtract extends BinaryOperator{

    public Subtract(Expression left, Expression right) {
        super(left, right, "-", 1);
    }

    @Override
    public int evaluate(int x) {
        return this.left.evaluate(x) - this.right.evaluate(x);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Subtract)
            return (this.left.equals(((Subtract) object).left) && this.right.equals(((Subtract) object).right));
        else return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(left, right, symbol);
    }
}

