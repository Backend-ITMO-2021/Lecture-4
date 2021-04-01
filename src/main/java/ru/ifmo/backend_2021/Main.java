package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.ExpressionParser;
import ru.ifmo.backend_2021.LexicalAnalyzer;
import ru.ifmo.backend_2021.Token;


public class Main {
    public static void main(String[] args) {
//        System.out.println(new Subtract(
//                new Multiply(
//                        new Const(2),
//                        new Variable("x")
//                ),
//                new Const(3)
//        ).evaluate(5));
//
//        System.out.println(new Subtract(
//                new Multiply(
//                        new Const(2),
//                        new Variable("x")
//                ),
//                new Const(3)
//        ).toString());
//
//        System.out.println(new Subtract(
//                new Multiply(
//                        new Const(2),
//                        new Variable("x")
//                ),
//                new Const(3)
//        ).toMiniString());

//        try {
//            int x = 10;
//            int answer = new Add(
//                    new Subtract(
//                            new Multiply(
//                                    new Variable("x"),
//                                    new Variable("x")),
//                            new Multiply(
//                                    new Variable("x"),
//                                    new Const(2))
//                    ),
//                    new Const(1)
//            ).evaluate(x);
//            System.out.println(answer);
//        } catch (NumberFormatException e) {
//            System.out.println("Argument is not number");
//        }


        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("(2 * (x / 1))");

        var curToken = lexicalAnalyzer.getCurToken();
        while (curToken != Token.END) {
            curToken = lexicalAnalyzer.nextToken();
            System.out.println(curToken);
        }

        var res = ExpressionParser.parse("(2 * (x / 1))");


        System.out.println(res.toMiniString());
        System.out.println(res.toString());
        System.out.println(res.evaluate(2));

    }
}