package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

import java.util.LinkedList;

enum TokenTypes {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    VARIABLE,
    NEGATIVE,
    CONSTANT,
    EOF
}


public class Parser {
    public static class TokenAcc {
        private int position;
        public LinkedList<Token> tokens;

        public TokenAcc(LinkedList<Token> tokens) {
            this.tokens = tokens;
        }

        public Token nextToken() {
            return tokens.get(position++);
        }

        public void back() {
            position--;
        }

        public int getPosition() {
            return position;
        }
    }

    public static LinkedList<Token> tokenize(String data) {
        int position = 0;
        LinkedList<Token> tokens = new LinkedList<>();
        int dataSize = data.length();
        while (position < dataSize) {
            switch (data.charAt(position)) {
                case '(':
                    tokens.add(new Token(TokenTypes.OPEN_BRACKET));
                    position++;
                    break;
                case ')':
                    tokens.add(new Token(TokenTypes.CLOSE_BRACKET));
                    position++;
                    break;
                case '*':
                    tokens.add(new Token(TokenTypes.MULTIPLY));
                    position++;
                    break;
                case '/':
                    tokens.add(new Token(TokenTypes.DIVIDE));
                    position++;
                    break;
                case '+':
                    tokens.add(new Token(TokenTypes.ADD));
                    position++;
                    break;
                case '-': // SUBTRACT or NEGATIVE
                    if (!tokens.isEmpty()
                            && (tokens.getLast().type == TokenTypes.CLOSE_BRACKET
                                || tokens.getLast().type == TokenTypes.CONSTANT
                                || tokens.getLast().type == TokenTypes.VARIABLE)) {
                        tokens.add(new Token(TokenTypes.SUBTRACT));
                    } else {
                        tokens.add(new Token(TokenTypes.NEGATIVE));
                    }
                    position++;
                    break;
                case ' ':
                    position++; //current token = null
                    break;
                default: // if CONST or VARIABLE
                    if (Character.isDigit(data.charAt(position))) { //CONSTANT
                        StringBuilder potentialConstantBuilder = new StringBuilder();
                        while(position < dataSize && Character.isDigit(data.charAt(position))) {
                            potentialConstantBuilder.append(data.charAt(position));
                            position++;
                        }
                        tokens.add(new Token(TokenTypes.CONSTANT, potentialConstantBuilder.toString()));
                    } else { //VARIABLE
                        StringBuilder potentialVariableBuilder = new StringBuilder();
                        while(position < dataSize && Character.isLetter(data.charAt(position))) {
                            potentialVariableBuilder.append(data.charAt(position));
                            position++;
                        }
                        tokens.add(new Token(TokenTypes.VARIABLE, potentialVariableBuilder.toString()));
                    }
            }
        }
        tokens.add(new Token(TokenTypes.EOF));

        return tokens;
    }

    public static Expression parse(LinkedList<Token> tokens) {
        TokenAcc tokenAcc = new TokenAcc(tokens);

        return expr(tokenAcc);
    }

    public static Expression expr(TokenAcc tokenAcc) {
        Token token = tokenAcc.nextToken();
        if (token.type == TokenTypes.EOF) {
            throw new RuntimeException("Неожиданный конец выражения");
        }
        tokenAcc.back();

        return trem(tokenAcc);
    }

    public static Expression trem(TokenAcc tokenAcc) { // + or -
        Expression expression = multdev(tokenAcc);
        while (true) {
            Token token = tokenAcc.nextToken();
            switch (token.type) {
                case ADD:
                    expression = new Add(expression, multdev(tokenAcc));
                    break;
                case SUBTRACT:
                    expression = new Subtract(expression, multdev(tokenAcc));
                    break;
                default:
                    tokenAcc.back();
                    return expression;
            }
        }
    }

    public static Expression multdev(TokenAcc tokenAcc) { // * or /
        Expression expression = factor(tokenAcc);
        while (true) {
            Token token = tokenAcc.nextToken();
            switch (token.type) {
                case MULTIPLY:
                    expression = new Multiply(expression, factor(tokenAcc));
                    break;
                case DIVIDE:
                    expression = new Divide(expression, factor(tokenAcc));
                    break;
                default:
                    tokenAcc.back();
                    return expression;
            }
        }
    }

    public static Expression factor(TokenAcc tokenAcc) { // CONST or expr or VARIABLE
        Token token  = tokenAcc.nextToken();
        switch (token.type) {
            case CONSTANT:
                return new Const(Integer.parseInt(token.tokenValue));
            case OPEN_BRACKET:
                Expression expression = expr(tokenAcc); //inner expression
                tokenAcc.nextToken();
                return expression;
            case VARIABLE:
                return new Variable(token.tokenValue);
            case NEGATIVE:
                Token constOrVariable = tokenAcc.nextToken();
                switch (constOrVariable.type) {
                    case CONSTANT:
                        return new Negative(new Const(Integer.parseInt(constOrVariable.tokenValue)));
                    case VARIABLE:
                        return new Negative(new Variable(constOrVariable.tokenValue));
                }
            default:
                return  new Const(0); //TODO default return
        }
    }
}
