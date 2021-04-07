package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public abstract class Operations extends Expression {

    Expression var1, var2;
    String sign;

    public Operations(String sign, Expression var1, Expression var2) {
        this.sign = sign;
        this.var1 = var1;
        this.var2 = var2;
    }

    abstract int evaluate(int var1, int var2);

    @Override
    public int evaluate(int x) {
        return evaluate(var1.evaluate(x), var2.evaluate(x));
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) {
        return evaluate(var1.evaluateWithVariables(variables), var2.evaluateWithVariables(variables));
    }
    @Override
    public String toString() {
        String result = "(" + var1.toString() + " " + sign + " " + var2.toString() + ")";
        return result;
    }
    @Override
    public String toMiniString() {
        String result = var1.toMiniString() + " " + sign + " " + var2.toMiniString();
        return result;
    }


    @Override
    public boolean equals(Object comparable) {
        if (comparable == null || comparable.getClass() != this.getClass()) {
            return false;
        }
        Operations oper = (Operations) comparable;
        return var1.equals(oper.var1) && var2.equals(oper.var2);
    }

    @Override
    public int hashCode() {
        return var1.hashCode() + var2.hashCode() + sign.hashCode();
    }
}