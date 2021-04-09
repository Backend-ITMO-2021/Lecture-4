package ru.ifmo.backend_2021.expressions;

import java.util.Map;
import java.util.Objects;

public class Const extends Expression{
    protected Integer value;

    public Const(Integer value) {
        super(0);
        this.value = value;
    }

    public String toString() {
      return value.toString();
    }

    @Override
    public String toMiniString() {
        return value.toString();
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int evaluateWithVariables(Map<String, Integer> variables) { return value;}

    @Override
    public boolean equals(Object object) {
        if (object instanceof Const)
            return value.equals(((Const) object).value);
        else return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }
}
