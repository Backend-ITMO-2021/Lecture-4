package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

import java.util.*;

import static ru.ifmo.backend_2021.expressions.ExpressionConstants.*;

public class ExpressionParser {
    private static int position;
    private static Set<String> singleSymbolsSet = ExpressionConstants.getSingleSymbols();

    public static Expression parse(String s) {
        LinkedList<String> tokens = tokenize(s);
        return addSubtractStep(tokens);
    }

    private static LinkedList<String> tokenize(String expression) {
        LinkedList<String> tokens = new LinkedList<>();
        int pos = 0;
        while (pos < expression.length()) {
            char c = expression.charAt(pos);
            if (singleSymbolsSet.contains(String.valueOf(c))) {
                tokens.add(String.valueOf(c));
                pos++;
            } else if (c <= '9' && c >= '0') {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(c);
                    pos++;
                    if (pos >= expression.length()) {
                        break;
                    }
                    c = expression.charAt(pos);
                } while (c <= '9' && c >= '0');
                tokens.add(sb.toString());
            } else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(c);
                    pos++;
                    if (pos >= expression.length()) {
                        break;
                    }
                    c = expression.charAt(pos);
                } while (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z');
                tokens.add(sb.toString());
            } else {
                if (c != ' ') {
                    throw new RuntimeException("Unexpected character: " + c);
                }
                pos++;
            }
        }
        return tokens;
    }

    public static Expression addSubtractStep(LinkedList<String> tokens) {
        Expression expression = multiplyDivideStep(tokens);
        while (true) {
            String token = tokens.peekFirst();
            if (token == null){
                return expression;
            } else if (token.equals(ADD_SYMBOL)) {
                tokens.removeFirst();
                expression = new Add(expression, multiplyDivideStep(tokens));
            } else if (token.equals(SUB_SYMBOL)) {
                tokens.removeFirst();
                expression = new Subtract(expression, multiplyDivideStep(tokens));
            } else if (singleSymbolsSet.contains(token)) {
                return expression;
            } else {
                throw new RuntimeException("Unexpected token: " + token);
            }
        }
    }

    public static Expression multiplyDivideStep(LinkedList<String> tokens) {
        Expression expression = term(tokens);
        while (true) {
            String token = tokens.peekFirst();
            if (token == null){
                return expression;
            } else if (token.equals(MUL_SYMBOL)) {
                tokens.removeFirst();
                expression = new Multiply(expression, term(tokens));
            } else if (token.equals(DIV_SYMBOL)) {
                tokens.removeFirst();
                expression = new Divide(expression, term(tokens));
            } else if (singleSymbolsSet.contains(token)) {
                return expression;
            } else {
                throw new RuntimeException("Unexpected token: " + token);
            }
        }
    }

    public static Expression term(LinkedList<String> tokens) {
        String token = tokens.pollFirst();
        if (token.equals(UNARY_NEG_SYMBOL)) {
            return new NegativeUnary(term(tokens));
        } else if (isNumeric(token)) {
            return new Const(Integer.parseInt(token));
        } else if (isVariable(token)) {
            return new Variable(token);
        } else if (token.equals(OPENING_BRACKET)) {
            Expression expression = addSubtractStep(tokens);
            token = tokens.pollFirst();
            if (token.equals(CLOSING_BRACKET)) {
                return expression;
            }
        }
        throw new RuntimeException("Unexpected token: " + token);
    }

    private static boolean isNumeric(String number) {
        return number.chars().allMatch(Character::isDigit);
    }

    private static boolean isVariable(String variableName) {
        return variableName.chars().allMatch(Character::isLetter);
    }
}

