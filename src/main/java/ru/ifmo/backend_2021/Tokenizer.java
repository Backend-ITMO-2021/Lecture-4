package ru.ifmo.backend_2021;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final List<Token> tokens = new ArrayList<>();
    private int curr = -1;
    private String expr;

    public Tokenizer(String expr) {
        this.expr = expr;
        tokenize(expr);
        tokens.add(new Token(TokenType.END, "end of expression"));
    }

    public boolean hasNext() {
        return curr < tokens.size() - 1;
    }

    public Token next() {
        return tokens.get(++curr);
    }

    public Token prev() {
        return tokens.get(--curr);
    }

    public Token current() {
        return tokens.get(curr);
    }

    public boolean isFirst() {
        return curr == 0;
    }

    private void tokenize(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                continue;
            }
            switch (s.charAt(i)) {
                case '(':
                    tokens.add(new Token(TokenType.LEFT_BR, "("));
                    break;

                case ')':
                    tokens.add(new Token(TokenType.RIGHT_BR, ")"));
                    break;

                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+"));
                    break;

                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-"));
                    break;

                case '*':
                    tokens.add(new Token(TokenType.MUL, "*"));
                    break;

                case '/':
                    tokens.add(new Token(TokenType.DIV, "/"));
                    break;


                default:

                    int j = i;
                    if (Character.isLetter(s.charAt(i))) {
                        while (j < s.length() && Character.isLetter(s.charAt(j))) {
                            j++;
                        }
                        tokens.add(new Token(TokenType.VARIABLE, s.substring(i, j)));

                    } else if ((Character.isDigit(s.charAt(i)))) {

                        while (j < s.length() && Character.isDigit(s.charAt(j))) {
                            j++;
                        }

                        tokens.add(new Token(TokenType.CONST, s.substring(i, j)));
                    }
                    i = j - 1;
            }
        }
    }
}
