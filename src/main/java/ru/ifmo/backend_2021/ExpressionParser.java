package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

public class ExpressionParser {

    private static Tokenizer tokens;

    public static Expression parse(String expression) {
        tokens = new Tokenizer(expression);
        return thirdLevel();
    }


    private static Expression thirdLevel() {
        Expression third = secondLevel();

        while (tokens.hasNext()) {
            Token operation = tokens.next();

            switch (operation.getType()) {
                case PLUS:
                    third = new Add(third, secondLevel());
                    break;

                case MINUS:
                    third = new Subtract(third, secondLevel());
                    break;

                default:
                    tokens.prev();
                    return third;
            }
        }

        return third;
    }

    private static Expression secondLevel() {
        Expression second = firstLevel();

        while (tokens.hasNext()) {
            Token operation = tokens.next();

            switch (operation.getType()) {
                case MUL:
                    second = new Multiply(second, firstLevel());
                    break;

                case DIV:
                    second = new Divide(second, firstLevel());
                    break;

                default:
                    tokens.prev();
                    return second;
            }
        }
        return second;
    }

    private static Expression firstLevel() {
        Token token = tokens.next();
        Expression firstLevel = null;

        switch (token.getType()) {

            case CONST:
                firstLevel = new Const(Integer.parseInt(token.getValue()));
                break;

            case VARIABLE:
                firstLevel = new Variable(token.getValue());
                break;

            case MINUS:
                if (tokens.hasNext() && tokens.next().getType() == TokenType.CONST) {
                    String number = "-" + tokens.current().getValue();
                    firstLevel = new Const(Integer.parseInt(number));
                } else {
                    tokens.prev();
                    firstLevel = new Negate(firstLevel());
                }
                break;

            case LEFT_BR:
                firstLevel = thirdLevel();
                tokens.next(); // eat right bracket
                break;
        }
        return firstLevel;
    }
}
