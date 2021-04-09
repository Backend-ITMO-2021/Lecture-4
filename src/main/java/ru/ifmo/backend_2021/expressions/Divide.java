package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

public class Divide extends BinaryOperator{

    public Divide(Expression left, Expression right) {
        super(left, right, "/", 2);
    }

    @Override
    public int evaluate(int x) {
        return this.left.evaluate(x) / this.right.evaluate(x);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Divide)
            return (this.left.equals(((Divide) object).left) && this.right.equals(((Divide) object).right));
        else return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(left, right, symbol);
    }
}

