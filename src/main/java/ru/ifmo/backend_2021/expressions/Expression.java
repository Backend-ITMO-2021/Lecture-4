package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public interface Expression {
  int evaluate(int x);

//  int evaluateWithVariables(Map<String, Integer> variables);

  String toMiniString();
}
