package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

import java.util.ArrayList;

public class ExpressionParser {
  private static int pos = 0;

  public static Expression parse(String s) {
    pos = 0;
    ArrayList<Token> tokens = getTokens(s.replaceAll(" ", ""));
    return getExpression(tokens);
  }

  public static ArrayList<Token> getTokens(String s) {
    ArrayList<Token> tokenList = new ArrayList<>();
    var counter = 0;
    while (counter < s.length()) {
      char symbol = s.charAt(counter);
      switch (symbol) {
        case '+':
          tokenList.add(new Token(OperationType.PLUS, Character.toString(symbol)));
          counter += 1;
          continue;
        case '-':
          tokenList.add(new Token(OperationType.MINUS, Character.toString(symbol)));
          counter += 1;
          continue;
        case '*':
          tokenList.add(new Token(OperationType.MULTIPLY, Character.toString(symbol)));
          counter += 1;
          continue;
        case '/':
          tokenList.add(new Token(OperationType.DIVISION, Character.toString(symbol)));
          counter += 1;
          continue;
        case '(':
          tokenList.add(new Token(OperationType.LEFT_BR, Character.toString(symbol)));
          counter += 1;
          continue;
        case ')':
          tokenList.add(new Token(OperationType.RIGHT_BR, Character.toString(symbol)));
          counter += 1;
          continue;
      }
      if (Character.isLetter(symbol)) {
        StringBuilder variable = new StringBuilder();
        while (Character.isLetter(symbol)) {
          variable.append(symbol);
          counter += 1;
          if (counter >= s.length()) {
            break;
          }
          symbol = s.charAt(counter);
        }
        tokenList.add(new Token(OperationType.VARIABLE, variable.toString()));
      }
      if (Character.isDigit(symbol)) {
        StringBuilder number = new StringBuilder();
        while (Character.isDigit(symbol)) {
          number.append(symbol);
          counter += 1;
          if (counter >= s.length()) {
            break;
          }
          symbol = s.charAt(counter);
        }
        tokenList.add(new Token(OperationType.CONST, number.toString()));
      }
    }
    tokenList.add(new Token(OperationType.FINAL, ""));
    return tokenList;
  }

  private static Expression getExpression(ArrayList<Token> tokens) {
    return firstOperation(tokens);
  }

  private static Expression firstOperation(ArrayList<Token> tokens) {
    Expression current = secondOperation(tokens);
    while (true) {
      Token nextToken = tokens.get(pos++);
      switch (nextToken.type) {
        case MINUS:
          current = new Subtract(current, secondOperation(tokens));
          break;
        case PLUS:
          current = new Add(current, secondOperation(tokens));
          break;
        case FINAL:
        case RIGHT_BR:
          return current;
      }
    }
  }

  private static Expression secondOperation(ArrayList<Token> tokens) {
    Expression current = thirdOperation(tokens);
    while (true) {
      Token nextToken = tokens.get(pos++);
      switch (nextToken.type) {
        case MULTIPLY:
          current = new Multiply(current, thirdOperation(tokens));
          break;
        case DIVISION:
          current = new Divide(current, thirdOperation(tokens));
          break;
        case FINAL:
        case MINUS:
        case PLUS:
        case RIGHT_BR:
          pos -= 1;
          return current;
      }
    }
  }

  private static Expression thirdOperation(ArrayList<Token> tokens) {
    Token currentToken = tokens.get(pos++);
    switch (currentToken.type) {
      case CONST:
        return new Const(Integer.parseInt(currentToken.value));
      case VARIABLE:
        return new Variable(currentToken.value);
      case MINUS:
        Token nextToken = tokens.get(pos++);
        if (nextToken.type == OperationType.CONST) {
          return new Const(Integer.parseInt("-" + nextToken.value));
        } else {
          return new Subtract(new Const(0), new Variable(nextToken.value));
        }
      case LEFT_BR:
        return firstOperation(tokens);
      default:
        throw new RuntimeException("Unexpected token:" + currentToken.value);
    }
  }
}
