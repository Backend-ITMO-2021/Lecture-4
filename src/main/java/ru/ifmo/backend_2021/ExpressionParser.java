package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

public class ExpressionParser {
    private static String s;
    private static int pos = 0;

    public static Expression parse(String s) {
        ExpressionParser.s = s.replaceAll("\\s+", "");
        pos = 0;
        Expression result = expression();
        if (pos != ExpressionParser.s.length()) {
            throw new IllegalStateException("Error in expression at " + pos);
        }
        return result;
    }

    private static Expression expression() {
        final String CHARS_BEFORE_UNARY_MINUS = "*/(";
        Expression first = term();
        while (pos < s.length()) {
            char operator = s.charAt(pos);
            if (operator != '+' && operator != '-') {
                break;
            } else if (operator == '-' && (pos == 0 || CHARS_BEFORE_UNARY_MINUS.contains(String.valueOf(s.charAt(pos - 1))))) {
                break; // it's unary minus
            } else {
                pos++;
            }

            Expression second = term();
            if (operator == '+') {
                first = new Add(first, second);
            } else {
                first = new Subtract(first, second);
            }
        }
        return first;
    }

    private static Expression term() {
        Expression first = factor();
        while (pos < s.length()) {
            char operator = s.charAt(pos);
            if (operator != '*' && operator != '/') {
                break;
            } else {
                pos++;
            }

            Expression second = factor();
            if (operator == '*') {
                first = new Multiply(first, second);
            } else {
                first = new Divide(first, second);
            }
        }
        return first;
    }

    private static Expression factor() {
        char next = s.charAt(pos);
        Expression result = null;
        if (next == '(') {
            pos++;
            result = expression();
            char closingBracket;
            if (pos < s.length()) {
                closingBracket = s.charAt(pos);
            } else {
                throw new IllegalStateException("Unexpected end of expression");
            }
            if (closingBracket == ')') {
                pos++;
                return result;
            }
            throw new IllegalStateException("')' expected but " + closingBracket);
        } else if (next == '-') {
            pos++;
            result = new UnaryMinus(factor());
        } else if (Character.isDigit(next)) {
            StringBuilder nextNumber = new StringBuilder();
            while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
                nextNumber.append(s.charAt(pos));
                pos++;
            }
            result = new Const(Integer.parseInt(nextNumber.toString()));
        } else {
            StringBuilder nextVar = new StringBuilder();
            while (pos < s.length() && Character.isLetterOrDigit(s.charAt(pos))) {
                nextVar.append(s.charAt(pos));
                pos++;
            }
            result = new Variable(nextVar.toString());
        }
        return result;
    }
}
