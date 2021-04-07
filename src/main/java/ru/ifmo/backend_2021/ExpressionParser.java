package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {

  String input;

  public static Expression parse(String s) {
    List<Lexeme> lexemes = lexAnalyze(s);
    LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
    return new ExpressionParser(s).plusminus(lexemeBuffer);
  }

  public ExpressionParser(String input) {
    this.input = input;
  }

  public enum LexemeType {
    LEFT_BRACKET, RIGHT_BRACKET,
    OP_PLUS, OP_MINUS, OP_MUL, OP_DIV,
    NUMBER, VAR,
    EOF;
  }

  public static class Lexeme {
    LexemeType type;
    String value;

    public Lexeme(LexemeType type, String value) {
      this.type = type;
      this.value = value;
    }

    public Lexeme(LexemeType type, Character value) {
      this.type = type;
      this.value = value.toString();
    }
  }

  public static class LexemeBuffer {
    private int pos;

    public List<Lexeme> lexemes;

    public LexemeBuffer(List<Lexeme> lexemes) {
      this.lexemes = lexemes;
    }

    public Lexeme next() {
      return lexemes.get(pos++);
    }

    public void back() {
      pos--;
    }
  }

  public static List<Lexeme> lexAnalyze(String expText) {
    ArrayList<Lexeme> lexemes = new ArrayList<>();
    int pos = 0;
    while (pos < expText.length()) {
      char c = expText.charAt(pos);
      switch (c) {
        case '(':
          lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
          pos++;
          continue;
        case ')':
          lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
          pos++;
          continue;
        case '+':
          lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
          pos++;
          continue;
        case '-':
          lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
          pos++;
          continue;
        case '*':
          lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
          pos++;
          continue;
        case '/':
          lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
          pos++;
          continue;
        default:
          if (c <= '9' && c >= '0') {
            StringBuilder sb = new StringBuilder();
            do {
              sb.append(c);
              pos++;
              if (pos >= expText.length()) {
                break;
              }
              c = expText.charAt(pos);
            } while (c <= '9' && c >= '0');
            lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
          } else if ((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A')) {
            StringBuilder var = new StringBuilder();
            var.append("x");
            do {
              pos++;
              if (pos >= expText.length()) {
                break;
              }
              c = expText.charAt(pos);
            } while ((c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A'));
            lexemes.add(new Lexeme(LexemeType.VAR, var.toString()));
          } else {
            pos++;
          }
      }
    }
    lexemes.add(new Lexeme(LexemeType.EOF, ""));
    return lexemes;
  }

  public static Expression plusminus(LexemeBuffer lexemes) {
    Expression value = multdiv(lexemes);
    while (true) {
      Lexeme lexeme = lexemes.next();
      switch (lexeme.type) {
        case OP_PLUS:
          value = new Add(value, multdiv(lexemes));
          break;
        case OP_MINUS:
          value = new Subtract(value, multdiv(lexemes));
          break;
        case EOF:
        case RIGHT_BRACKET:
          lexemes.back();
          return value;
      }
    }
  }

  public static Expression multdiv(LexemeBuffer lexemes) {
    Expression value = factor(lexemes);
    while (true) {
      Lexeme lexeme = lexemes.next();
      switch (lexeme.type) {
        case OP_MUL:
          value = new Multiply(value, factor(lexemes));
          break;
        case OP_DIV:
          value = new Divide(value, factor(lexemes));
          break;
        case EOF:
        case RIGHT_BRACKET:
        case OP_PLUS:
        case OP_MINUS:
          lexemes.back();
          return value;
      }
    }
  }

  public static Expression factor(LexemeBuffer lexemes) {
    Lexeme lexeme = lexemes.next();
    switch (lexeme.type) {
      case EOF:
      case OP_MINUS:
        return new Subtract(new Const(0), multdiv(lexemes));
      case NUMBER:
        return new Const(Integer.parseInt(lexeme.value));
      case VAR:
        return new Variable(lexeme.value);
      case LEFT_BRACKET:
        Expression value = plusminus(lexemes);
        lexemes.next();
        return value;
      default:
        throw new RuntimeException();
    }
  }
}