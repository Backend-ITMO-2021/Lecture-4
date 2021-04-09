package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;

import java.util.List;


public class ExpressionParser {
  public static Expression parse(String s) {
    String cleanString = s.replaceAll("\\s+", "");
    List<ExpressionAnalyzer> exprList = ExpressionAnalyzer.analyzer(cleanString);

    return expr(new ExpressionNavigator(exprList));
  }

  public static Expression expr(ExpressionNavigator exprList) {
    ExpressionAnalyzer curExpr = exprList.next();
    if (curExpr.type == SignType.EOF) {
      return null;
    } else {
      exprList.back();
      return plusminus(exprList);
    }
  }

  public static Expression plusminus(ExpressionNavigator exprList) {
    Expression value = multdiv(exprList);
    while (true) {
      ExpressionAnalyzer curExpr = exprList.next();
      switch (curExpr.type) {
        case PLUS:
          value = new Add(value, multdiv(exprList));
          break;
        case MINUS:
          value = new Subtract(value, multdiv(exprList));
          break;
        case EOF:
        case CLOSE:
          exprList.back();
          return value;
      }
    }
  }

  public static Expression multdiv(ExpressionNavigator exprList) {
    Expression value = factor(exprList);
    while (true) {
      ExpressionAnalyzer curExpr = exprList.next();
      switch (curExpr.type) {
        case MUL:
          value = new Multiply(value, factor(exprList));
          break;
        case DIV:
          value = new Divide(value, factor(exprList));
          break;
        case EOF:
        case CLOSE:
        case PLUS:
        case MINUS:
          exprList.back();
          return value;
      }
    }
  }

  public static Expression factor(ExpressionNavigator exprList) {
    ExpressionAnalyzer curExpr = exprList.next();
    switch (curExpr.type) {
      case VAR:
        return new Variable(curExpr.value);
      case NUMBER:
        return new Const(Integer.parseInt(curExpr.value));
      case MINUS:
        ExpressionAnalyzer nextExpr = exprList.next();
        if (nextExpr.type == SignType.VAR) {
          return new UnMinus(new Variable(nextExpr.value));
        } else {
          return new UnMinus(new Const(Integer.parseInt(nextExpr.value)));
        }
      case OPEN:
        Expression value = plusminus(exprList);
        curExpr = exprList.next();
        if (curExpr.type != SignType.CLOSE) {
          throw new RuntimeException("Unexpected token: " + curExpr.value
                  + " at position: " + exprList.getPos());
        }
        return value;
      default:
        throw new RuntimeException("Unexpected token: " + curExpr.value
                + " at position: " + exprList.getPos());
    }
  }
}
