package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;
import ru.ifmo.backend_2021.expressions.Expression;


import java.util.ArrayList;

public class ExpressionParser {
  private static ArrayList<String> tokens;
  private static int pos = 0;

  public static Expression parse(String s) {
    LexicalAnalyzer analyzer = new LexicalAnalyzer(s);
    tokens = analyzer.tokenize();
//    System.out.println(tokens);
    pos = 0;
    return expression();
  }

  private static Expression expression() {
    Expression first = term();

    while (pos < tokens.size()) {
      String operator = tokens.get(pos);
      if (!operator.equals("+") && !operator.equals("-")) {
        break;
      }

      pos++;
      Expression second = term();
      if (operator.equals("+")) {
        first = new Add(first, second);
      } else {
        first = new Subtract(first, second);
      }
    }

    return first;
  }

  private static Expression term() {
    Expression first = factor();

    while (pos < tokens.size()) {
      String operator = tokens.get(pos);
      if (!operator.equals("*") && !operator.equals("/")) {
        break;
      }

      pos++;
      Expression second = factor();
      if (operator.equals("*")) {
        first = new Multiply(first, second);
      } else {
        first = new Divide(first, second);
      }
    }

    return first;
  }

  private static Expression factor() {
    String next = tokens.get(pos);
    Expression result;

    if (next.equals("(")) {
      pos++;
      result = expression();
      String closingBracket;

      if (pos < tokens.size()) {
        closingBracket = tokens.get(pos);
      } else {
        throw new IllegalStateException("Unexpected end of expression");
      }
      if (pos < tokens.size() && closingBracket.equals(")")) {
        pos++;
        return result;
      }
      throw new IllegalStateException("')' expected but " + closingBracket);
    } else if (next.equals("-")) {
      // Unary Minus
      pos++;
      next = tokens.get(pos);

      if (next.equals("(")) {
        result = expression();
      } else {
        result = factor();
      }

      return new UnaryMinus(result);
    }

    pos++;

    // Number or Variable
    try {
      return new Const(Integer.parseInt(next));
    } catch (NumberFormatException nfe) {
      return new Variable(next);
    }
  }
};
