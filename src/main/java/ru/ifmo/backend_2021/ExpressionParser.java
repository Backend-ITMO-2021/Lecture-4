package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;
import static ru.ifmo.backend_2021.Constants.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;



public class ExpressionParser {
    private int position = 0;
    private final LinkedList<String> tokens;
    
    public ExpressionParser(String input) {
        this.tokens = tokenize(input);
    }

    public static Expression parse(String input) {
        ExpressionParser expPars = new ExpressionParser(input);
        return expPars.expression();
    }

    private LinkedList<String> tokenize(String input) {
        Pattern pattern = Pattern.compile(REGEX_STRING);
        Matcher matcher = pattern.matcher(input.replace(" ", ""));
        LinkedList<String> tokens = new LinkedList<>();
        while (matcher.find()) {
            String token = matcher.group();
            tokens.add(token);
        }
        return tokens;
    }

    private Expression expression() {
        Expression first = term();

        while (position < tokens.size()) {
            String operator = tokens.get(position);
            if (!OPERATORS.contains(operator)) {
                break;
            } else {
                position++;
            }
            Expression second = term();
            return new BinaryOperator(first, second, operator);
        }
        return first;
    }

    private Expression term() {
        Expression first = factor();

        while (position < tokens.size()) {
            String operator = tokens.get(position);
            if (!operator.equals(MUL_SYMBOL) && !operator.equals(DIV_SYMBOL)) {
                break;
            } else {
                position++;
            }
            Expression second = factor();
            return new BinaryOperator(first, second, operator);
        }
        return first;
    }

    private Expression factor() {
        String next = tokens.get(position);
        Expression result;
        if (next.equals(OPENING_BRACKET)) {
            position++;
            result = expression();
            String closingBracket;
            closingBracket = tokens.get(position);
            if (closingBracket.equals(CLOSING_BRACKET)) {
                position++;
                return result;
            }
        }
        position++;
        try {
            return new Const(Integer.valueOf(next));
        } catch (NumberFormatException ex) {
            if (next.charAt(0) == SUB_CHAR) {
                return new NegativeUnary(new Variable(next.substring(1)));
            }
            return new Variable(next);
        }
    }
}