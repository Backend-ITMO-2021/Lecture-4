package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public abstract class Expression {
  int EXPRESSION_PRIORITY = 0;

  public abstract int evaluate(int x);

  public abstract int evaluateWithVariables(Map<String, Integer> variables);

  public abstract String toMiniString();

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();
}
