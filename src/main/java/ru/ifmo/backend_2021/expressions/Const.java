package ru.ifmo.backend_2021.expressions;

import java.util.Objects;

public class Const extends Expression{

    private final int constValue;
    public Const(Integer value) {
        this.constValue = value;
        this.SYMBOL = "";
        this.PRIORITY = 0;
    }

    @Override
    public int evaluate(int x) {
        return this.constValue;
    }

    @Override
    public String toString() {
        return "" + constValue;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Const)) return false;
        Const aConst = (Const) o;
        return Objects.equals(constValue, aConst.constValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constValue);
    }
}
