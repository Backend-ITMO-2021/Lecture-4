package ru.ifmo.backend_2021.expressions;

public class Add extends BinaryExpression{

    public Add(Expression val1, Expression val2){
        super(val1, val2, '+');
    }

    @Override
    public int evaluate(int x) {
        return val1.evaluate(x) + val2.evaluate(x);
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toMiniString() {
        return super.toMiniString();
    }
}
