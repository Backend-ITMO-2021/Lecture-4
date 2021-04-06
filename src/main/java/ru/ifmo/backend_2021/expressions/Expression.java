package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public abstract class Expression {
  protected int priority;

  Expression(int priority) {
    this.priority = priority;
  }

  public abstract int evaluate(int x);

  public int evaluateWithVariables(Map<String, Integer> variables) {
    return -1;
  }

  public abstract String toMiniString();

  public abstract boolean equals(Expression expression);

  public abstract int hashCode();
}
