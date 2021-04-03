package ru.ifmo.backend_2021.expressions;

public class Divide extends Expression{

    public Divide(Expression... elements){
        super(elements);
        this.SYMBOL = " / ";
        this.PRIORITY = 2;
    }

    @Override
    public int evaluate(int x) {
        return elements[0].evaluate(x) / elements[1].evaluate(x);
    }
}
