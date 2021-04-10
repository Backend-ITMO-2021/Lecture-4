package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

public class ExpressionParser {

    String inputExpression;
    int position = 0;

    public static Expression parse(String s) {
        return new ExpressionParser(s.replaceAll(" ", "")).addSub();
    }

    public ExpressionParser(String inputExpression) {
        this.inputExpression = inputExpression;
    }

    private Expression addSub() {
        Expression expression = mulDiv();

        while (true) {
            char current = currentChar();

            switch (current) {
                case Utils.ADD -> expression = new Add(expression, mulDiv());
                case Utils.SUB -> expression = new Subtract(expression, mulDiv());
                default -> {
                    return expression;
                }
            }
        }
    }

    private Expression mulDiv() {
        Expression expression = varConst();

        while (true) {
            char current = currentChar();

            switch (current) {
                case Utils.MUL -> expression = new Multiply(expression, varConst());
                case Utils.DIV -> expression = new Divide(expression, varConst());
                default -> {
                    return expression;
                }
            }
        }
    }

    private Expression varConst() {
        char next = nextChar();

        if (next == Utils.SUB) return new Subtract(new Const(0), varConst());

        if (next == Utils.LEFT_B) {
            Expression expression = addSub();
            nextChar();
            return expression;
        }

        if (Character.isDigit(next)) return getConstValue();
        if (Character.isLetter(next)) return variable();

        return null;
    }

    private Expression getConstValue() {
        char c = currentChar();
        StringBuilder stringBuilder = new StringBuilder();
        while (Character.isDigit(c)) {
            stringBuilder.append(c);
            c = nextChar();
        }
        return new Const(Integer.parseInt(stringBuilder.toString()));
    }

    private Expression variable() {
        char currentChar = currentChar();

        StringBuilder stringBuilder = new StringBuilder();
        while (Character.isAlphabetic(currentChar)) {
            stringBuilder.append(currentChar);
            currentChar = nextChar();
        }

        return new Variable(stringBuilder.toString());
    }

    private char currentChar() {
        if (position > inputExpression.length()) return 0;
        return inputExpression.charAt(position - 1);
    }

    private char nextChar() {
        position++;
        return currentChar();
    }
}
