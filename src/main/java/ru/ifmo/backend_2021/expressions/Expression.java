package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public interface Expression {
  int evaluate(int x);

  int evaluateWithVariables(Map<String, Integer> variables);

  String toMiniString();
  String toMiniString(int parentPriority, String parentOperation, boolean atRight);

  static int priority(Expression expression) {
    if (expression instanceof Add) return 1;
    if (expression instanceof Subtract) return 1;
    if (expression instanceof Multiply) return 2;
    if (expression instanceof Divide) return 2;
    if (expression instanceof UnaryMinus) return 3;

    return 0;
  }
}
