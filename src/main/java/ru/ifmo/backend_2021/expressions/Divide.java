package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public class Divide extends BinaryExpression{

    public Divide(Expression val1, Expression val2){
        super(val1, val2, '/');
    }

    @Override
    public int evaluate(int x) {
        return val1.evaluate(x) / val2.evaluate(x);
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toMiniString() {
        if ((val1 instanceof Subtract || val1 instanceof Add) && !(val2 instanceof Const || val2 instanceof Variable )){
            return ("(" + this.val1.toMiniString() + ") / (" + this.val2.toMiniString() + ")");
        }
        if (val1 instanceof Subtract || val1 instanceof Add){
            return ("(" + this.val1.toMiniString() + ") / " + this.val2.toMiniString() + "");
        }
        if(!(val2 instanceof Const || val2 instanceof Variable)){
            return (this.val1.toMiniString() + " / (" + this.val2.toMiniString() + ")");
        }
        return super.toMiniString();
    }
}
