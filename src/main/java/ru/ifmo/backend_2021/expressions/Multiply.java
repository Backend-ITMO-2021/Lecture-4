package ru.ifmo.backend_2021.expressions;

public class Multiply extends BinaryExpression{

    public Multiply(Expression val1, Expression val2){
        super(val1, val2, '*');
    }

    @Override
    public int evaluate(int x) {
        return val1.evaluate(x) * val2.evaluate(x);
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toMiniString() {
        if ((val1 instanceof Subtract || val1 instanceof Add) && (val2 instanceof Subtract || val2 instanceof Add || val2 instanceof Divide)){
            return ("(" + this.val1.toMiniString() + ") * (" + this.val2.toMiniString() + ")");
        }
        if (val1 instanceof Subtract || val1 instanceof Add){
            return ("(" + this.val1.toMiniString() + ") * " + this.val2.toMiniString() + "");
        }
        if (val2 instanceof Subtract || val2 instanceof Add || val2 instanceof Divide){
            return (this.val1.toMiniString() + " * (" + this.val2.toMiniString() + ")");
        }
        return super.toMiniString();
    }
}
