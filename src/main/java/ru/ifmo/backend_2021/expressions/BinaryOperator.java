package ru.ifmo.backend_2021.expressions;

public class BinaryOperator extends Expression{
    protected Expression left;
    protected Expression right;
    protected String symbol;

    public BinaryOperator(Expression left, Expression right, String symbol, int priority) {
        super(priority);
        this.left = left;
        this.right = right;
        this.symbol = symbol;
    }

    @Override
    public String toString(){
        return "(" + left.toString() + " " + this.symbol + " " + right.toString() + ")";
    }

    @Override
    public String toMiniString(){
        if (this.priority > left.priority && left.priority > 0) {
            if ((this.priority > right.priority && right.priority > 0) ||
                    (this.priority == right.priority && (this instanceof Subtract || this instanceof Divide ||
                            (this.getClass() != right.getClass()))))
                return "(" + left.toMiniString() + ") " + this.symbol + " (" + right.toMiniString() + ")";
            return "(" + left.toMiniString() + ") " + this.symbol + " " + right.toMiniString();
        }
        else if ((this.priority > right.priority && right.priority > 0) ||
                (this.priority == right.priority && (this instanceof Subtract || this instanceof Divide ||
                        (this.getClass() != right.getClass()))))
            return left.toMiniString() + " " + this.symbol + " (" + right.toMiniString() + ")";
        else return left.toMiniString() + " " + this.symbol + " " + right.toMiniString();
    }

//    @Override
//    public String toMiniString(){
//        String str;
//        if (this.priority > left.priority && left.priority > 0)
//            str = "(" + left.toMiniString() + ") " + this.symbol;
//        else
//            str = left.toMiniString() + " " + this.symbol;
//        if ((this.priority > right.priority && right.priority > 0) ||
//                (this.priority == right.priority && (this instanceof Subtract || this instanceof Divide ||
//                (this.getClass() != right.getClass()))))
//            return str + " (" + right.toMiniString() + ")";
//        else
//            return str + " " + right.toMiniString();
//    }

}
