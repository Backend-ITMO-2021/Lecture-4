package ru.ifmo.backend_2021;

public class Token {
    TokenTypes type;
    String tokenValue;

    public Token(TokenTypes tokenType) {
        this.type = tokenType;
        this.tokenValue = "";

    }

    public Token(TokenTypes tokenType, String tokenValue) {
        this.type = tokenType;
        this.tokenValue = tokenValue;
    }
}
