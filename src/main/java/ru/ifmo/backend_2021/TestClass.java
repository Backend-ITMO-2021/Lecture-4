package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

import java.util.Map;


public class TestClass {
    public static void main(String[] args) {
        Expression vx = new Variable("x");
        Expression vy = new Variable("y");
        Expression vz = new Variable("z");
        Expression c2 = new Const(2);
        Expression expressionA = new Divide(new Subtract(vy, vx), new Multiply(c2, vx));
        Expression expressionB = new Divide(new Subtract(vy, vx), new Multiply(c2, vx));
        Expression expressionC = new Divide(new Subtract(vy, vx), new Multiply(c2, vz));

        System.out.println(expressionA.evaluateWithVariables(Map.of("x", 1, "y", 10)));
        System.out.println(expressionA.toMiniString());

    }
}
