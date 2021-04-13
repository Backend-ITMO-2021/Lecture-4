package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

import java.util.HashMap;
import java.util.Map;


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


//        System.out.println(new Multiply(
//                new Subtract(
//                        new Const(2),
//                        new Variable("x")
//                ),
//                new Const(3)
//        ).toMiniString());


//        System.out.println(new Multiply(new Const(2), new Variable("x"))
//                .equals(new Multiply(new Const(2), new Variable("x"))));
//
//        System.out.println(new Multiply(new Const(2), new Variable("x"))
//                .equals(new Multiply(new Variable("x"), new Const(2))));
//
//        System.out.println(new Multiply(new Const(3), new Variable("x")).hashCode());


//        Expression test = new Multiply(
//                new Add(
//                        new Const(2),
//                        new UnaryMinus(
//                                new Variable("x")
//                        )
//                ),
//                new Const(3)
//        );

//        System.out.println(test.toString());
//        System.out.println(test.toMiniString());


//        LexicalAnalyzer analyzer = new LexicalAnalyzer("(2 + testMe) * (-19) / divider");
//        System.out.println(analizer.tokenize());


//        Expression res = ExpressionParser.parse("-(2 + testMe) * -51");
//        System.out.println(res.toMiniString());


//        Map<String, Integer> variables = new HashMap<>();
//        variables.put("x", 3);
//        variables.put("y", 7);
        Expression exp = ExpressionParser.parse("( ( (( x)  ))+2) ");
        System.out.println(exp.evaluate(0));
    }
}
