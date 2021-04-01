package ru.ifmo.backend_2021;

public class LexicalAnalyzer {
    private String expression;
    private int index = 0;

    private Token curToken = Token.BEGIN;

    public LexicalAnalyzer(String expression) {
        this.expression = expression;
    }

    public int value;
    public String varName;

    private void skipWhiteSpaces() {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
    }

    public Token nextToken() {
        skipWhiteSpaces();
        if (index >= expression.length()) {
            curToken = Token.END;
            return curToken;
        }

        char ch = expression.charAt(index);
        switch (ch) {
            case '-':
                if (curToken == Token.NUMBER || curToken == Token.VARIABLE || curToken == Token.RP) {
                    curToken = Token.SUB;
                } else {
                    curToken = Token.NEGATE;
                }
                break;
            case '+':
                curToken = Token.ADD;
                break;
            case '*':
                curToken = Token.MUL;
                break;
            case '/':
                curToken = Token.DIV;
                break;
            case '(':
                curToken = Token.LP;
                break;
            case ')':
                curToken = Token.RP;
                break;
            default:
                if (Character.isDigit(ch)) {
                    int l = index;
                    while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                        index++;
                    }
                    int r = index;
                    if (index == expression.length()) {
                        index++;
                    }
                    value = Integer.parseUnsignedInt(expression.substring(l, r));
                    curToken = Token.NUMBER;
                    index--;
                } else if (Character.isLetter(ch)) {
                    int l = index;
                    while (index < expression.length() && Character.isLetter(expression.charAt(index))) {
                        index++;
                    }
                    int r = index;
                    if (index == expression.length()) {
                        index++;
                    }
                    varName = expression.substring(l, r);
                    index--;
                    curToken = Token.VARIABLE;
                } else {
                    curToken = Token.MISTAKE;
                }
        }
        index++;
        return curToken;
    }

    public Token getCurToken() {
        return curToken;
    }
}
