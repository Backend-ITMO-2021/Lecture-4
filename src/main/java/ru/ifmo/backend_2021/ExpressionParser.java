package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

public class ExpressionParser {

    private static LexicalAnalyzer lexicalAnalyzer;

    public static Expression parse(String s) {
        lexicalAnalyzer = new LexicalAnalyzer(s);
        return addSub();
    }

    private static Expression unary() {
        lexicalAnalyzer.nextToken();
        Expression res;
        switch (lexicalAnalyzer.getCurToken()) {
            case NUMBER:
                res = new Const(lexicalAnalyzer.value);
                lexicalAnalyzer.nextToken();
                break;
            case NEGATE:
                Expression rhs = unary();
                if (rhs instanceof Const) {
                    var old = ((Const) rhs).getValue();
                    ((Const) rhs).setValue(-old);
                    res = rhs;
                } else {
                    res = new Negate(rhs);
                }
                break;
            case VARIABLE:
                res = new Variable(lexicalAnalyzer.varName);
                lexicalAnalyzer.nextToken();
                break;
            case LP:
                res = addSub();
                lexicalAnalyzer.nextToken();
                break;
            default:
                return new Const(0);
        }
        return res;
    }

    private static Expression mulDiv() {
        Expression res = unary();
        do {
            switch (lexicalAnalyzer.getCurToken()) {
                case MUL:
                    res = new Multiply(res, unary());
                    break;
                case DIV:
                    res = new Divide(res, unary());
                    break;
                default:
                    return res;
            }
        } while (true);
    }

    private static Expression addSub() {
        Expression res = mulDiv();
        do {
            switch (lexicalAnalyzer.getCurToken()) {
                case ADD:
                    res = new Add(res, mulDiv());
                    break;
                case SUB:
                    res = new Subtract(res, mulDiv());
                    break;
                default:
                    return res;
            }
        } while (true);
    }

}
