package ru.ifmo.backend_2021.expressions;

public class Subtract extends Expression{

    public Subtract(Expression... elements){
        super(elements);
        this.SYMBOL = " - ";
        this.PRIORITY = 1;
    }

    @Override
    public int evaluate(int x) {
        return elements[0].evaluate(x) - elements[1].evaluate(x);
    }
}
