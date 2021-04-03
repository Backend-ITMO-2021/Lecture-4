package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
    private final String[] tokens;
    private int pos = 0;
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String DIVISION = "/";
    private static final String MULTIPLIER = "*";

    public ExpressionParser(String input) {
        this.tokens = getTokens(input);
    }

    public static Expression parse(String s) {
        ExpressionParser ep = new ExpressionParser(s);
        return ep.expression();
    }

    private String[] getTokens(String input) {
        Pattern p = Pattern.compile("(\\w*(?<![^/\\-+*(])-\\d+)|(\\d+)|(\\w*(?<![^/\\-+*(])-[a-zA-Z]+)|([a-zA-Z]+)|[/\\-+*()]");
        Matcher m = p.matcher(input.replace(" ", ""));
        List<String> tokens = new LinkedList<>();
        while (m.find()) {
            String token = m.group();
            tokens.add(token);
        }
        return tokens.toArray(String[]::new);
    }

    private Expression expression() {
        Expression first = term();

        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals(PLUS) && !operator.equals(MINUS)
                    && !operator.equals(MULTIPLIER) && !operator.equals(DIVISION)) {
                break;
            } else {
                pos++;
            }

            Expression second = term();
            switch (operator) {
                case PLUS: {
                    return new Add(first, second);
                }
                case MINUS: {
                    return new Subtract(first, second);
                }
                case MULTIPLIER: {
                    return new Multiply(first, second);
                }
                case DIVISION: {
                    return new Divide(first, second);
                }
            }
        }
        return first;
    }

    private Expression term() {
        Expression first = factor();

        while (pos < tokens.length) {
            String operator = tokens[pos];
            if (!operator.equals(MULTIPLIER) && !operator.equals(DIVISION)) {
                break;
            } else {
                pos++;
            }

            Expression second = factor();
            if (operator.equals(MULTIPLIER)) {
                return new Multiply(first, second);
            } else {
                return new Divide(first, second);
            }
        }
        return first;
    }

    private Expression factor() {
        String next = tokens[pos];
        Expression result;
        if (next.equals(OPEN_BRACKET)) {
            pos++;
            result = expression();
            String closingBracket;
            closingBracket = tokens[pos];
            if (closingBracket.equals(CLOSE_BRACKET)) {
                pos++;
                return result;
            }
        }
        pos++;
        try {
            return new Const(Integer.parseInt(next));
        } catch (NumberFormatException ex) {
            if (next.length() > 1 && next.charAt(0) == '-') {
                return new Subtract(new Const(0), new Variable(next.substring(1)));
            }
            return new Variable(next);
        }
    }
}
