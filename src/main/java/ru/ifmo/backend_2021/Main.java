package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Subtract(
                new Multiply(
                        new Const(2),
                        new Variable("x")
                ),
                new Const(3)
        ).evaluate(5));
    }
}
