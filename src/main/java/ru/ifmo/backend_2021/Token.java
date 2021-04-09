package ru.ifmo.backend_2021;

enum OperationType {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVISION,
    CONST,
    VARIABLE,
    LEFT_BR,
    RIGHT_BR,
    FINAL
}

public class Token {
    protected OperationType type;
    protected String value;

    public Token(OperationType type, String value) {
        this.type = type;
        this.value = value;
    }
}
