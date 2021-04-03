package ru.ifmo.backend_2021;
import ru.ifmo.backend_2021.expressions.*;

import java.util.ArrayList;

enum LexType {
    LB, RB, PLUS, MINUS, MUL, DIV, NUMBER, VAR, EOF
}

public class Lex {
    LexType type;
    String value;

    public Lex(LexType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    public Lex(LexType type, String value) {
        this.type = type;
        this.value = value;
    }

    public static class LexBuffer {
        private int pointer;

        public ArrayList<Lex> lexes;

        public LexBuffer(ArrayList<Lex> lexes) {
            this.lexes = lexes;
        }

        public Lex next() {
            return lexes.get(pointer++);
        }

        public void back() {
            pointer--;
        }

    }
    public static Expression expr(LexBuffer lexes) {
        return plusminus(lexes);
    }

    public static Expression plusminus(LexBuffer lexes) {
        Expression value = multdiv(lexes);
        while (true) {
            Lex lex = lexes.next();
            switch (lex.type) {
                case PLUS:
                    value = new Add(value, multdiv(lexes));
                    break;
                case MINUS:
                    value = new Subtract(value, multdiv(lexes));
                    break;
                case EOF:
                case RB:
                    return value;
                default:
                    throw new RuntimeException("Unexpected token:" + lex.value);
            }
        }
    }

    public static Expression multdiv(LexBuffer lexes) {
        Expression value = factor(lexes);
        while (true) {
            Lex lex = lexes.next();
            switch (lex.type) {
                case MUL:
                    value = new Multiply(value, factor(lexes));
                    break;
                case DIV:
                    value = new Divide(value, factor(lexes));
                    break;
                case EOF:
                case RB:
                case PLUS:
                case MINUS:
                    lexes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token:" + lex.value);
            }
        }
    }

    public static Expression factor(LexBuffer lexes) {
        Lex lex = lexes.next();
        switch (lex.type) {
            case VAR:
                return new Variable(lex.value);
            case NUMBER:
                return new Const(Integer.parseInt(lex.value));
            case LB:
                return plusminus(lexes);
            case MINUS:
                Lex next = lexes.next();
                if (next.type == LexType.VAR) {
                    return new Subtract(new Const(0), new Variable(next.value));
                } else {
                    return new Subtract(new Const(0), new Const(Integer.parseInt(next.value)));
                }

            default:
                throw new RuntimeException("Unexpected token:" + lex.value);
        }
    }
}
