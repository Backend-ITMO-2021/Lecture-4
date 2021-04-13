package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;


public class Main {
    public static void main(String[] args) {
//        System.out.println(new Subtract(
//                new Multiply(
//                        new Const(2),
//                        new Variable("x")
//                ),
//                new Const(3)
//        ).evaluate(5));

//        System.out.println(new Subtract(
//                new Multiply(
//                        new Const(2),
//                        new Variable("x")
//                ),
//                new Const(3)
//        ).toString());

        System.out.println(new Multiply(
                new Subtract(
                        new Const(2),
                        new Variable("x")
                ),
                new Const(3)
        ).toMiniString());
    }
}
