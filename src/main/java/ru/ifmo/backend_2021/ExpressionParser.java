package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

public class ExpressionParser {

    String input;
    int pos = 0;

    public static Expression parse(String s) {
        return new ExpressionParser(s.replaceAll("\\s+", "")).parse1();
    }

    public ExpressionParser(String input) {
        this.input = input;
    }

    Expression parse1() {
        Expression a = parse2();

        while (true) {
            char c = cur();

            switch (c) {
                case '+':
                    a = new Add(a, parse2());
                    break;

                case '-':
                    a = new Subtract(a, parse2());
                    break;

                default:
                    return a;
            }
        }
    }

    Expression parse2() {
        Expression a = val();

        while (true) {
            char c = cur();

            switch (c) {
                case '*':
                    a = new Multiply(a, val());
                    break;

                case '/':
                    a = new Divide(a, val());
                    break;

                default:
                    return a;
            }
        }
    }

    Expression val() {
        char c = next();

        if (c == '-') {
            return new Subtract(new Const(0), val());
        }

        if (c == '(') {
            Expression e = parse1();

            next();
            return e;
        }

        if (Character.isDigit(c)) {
            return pConst();
        }

        if (Character.isAlphabetic(c)) {
            return variable();
        }

        return null;
    }

    Expression pConst() {
        char c = cur();

        int a = 0;
        while (Character.isDigit(c)) {
            a = c - '0' + a * 10;
            c = next();
        }

        return new Const(a);
    }

    Expression variable() {
        char c = cur();

        StringBuilder name = new StringBuilder();
        while (Character.isAlphabetic(c)) {
            name.append(c);
            c = next();
        }

        return new Variable(name.toString());
    }

    char cur() {
        if (pos > input.length()) {
            return 0;
        }

        return input.charAt(pos - 1);
    }

    char next() {
        pos++;
        return cur();
    }
}
