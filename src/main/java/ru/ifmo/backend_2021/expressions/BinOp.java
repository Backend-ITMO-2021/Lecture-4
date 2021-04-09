package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

public abstract class BinOp extends Expression {
    private final Expression var1, var2;
    private final String sign;
    private final Integer priority;

    BinOp(Expression curVar1, Expression curVar2, String curSign, Integer curPriority){
        var1 = curVar1;
        var2 = curVar2;
        sign = curSign;
        priority = curPriority;
    }

    public abstract int solve(int num1, int num2);

    public int evaluate(int x) {
        return solve(var1.evaluate(x), var2.evaluate(x));
    }

    public String toString() {
        return "(" + var1.toString() + " " + sign + " " + var2.toString() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(var1, var2, sign);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinOp that = (BinOp) o;
        return Objects.equals(var1, that.var1) && Objects.equals(var2, that.var2) && Objects.equals(sign, that.sign);
    }

    static int getPriority(Expression expr) {
        if (expr instanceof UnMinus) return 0;
        if (expr instanceof Multiply) return 1;
        if (expr instanceof Divide) return 1;
        if (expr instanceof Add) return 2;
        if (expr instanceof Subtract) return 2;
        return 3;
    }

    public String toMiniString() {
        StringBuilder sb = new StringBuilder();

        if (getPriority(var1) > priority && !(var1 instanceof Variable || var1 instanceof Const)) {
            sb.append("(").append(var1.toMiniString()).append(")");
        } else {
            sb.append(var1.toMiniString());
        }
        sb.append(" ").append(sign).append(" ");

        if (!(var2 instanceof Variable || var2 instanceof Const)) {
            if ((getPriority(var2) > priority) || ((getPriority(var2) == priority) && (((var2 instanceof BinOp) && !sign.equals(((BinOp) var2).sign)) || (sign.equals("/") || sign.equals("-"))))) {
                sb.append("(").append(var2.toMiniString()).append(")");
            } else {
                sb.append(var2.toMiniString());
            }
        } else {
            sb.append(var2.toMiniString());
        }

        return sb.toString();
    }



}
