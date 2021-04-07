package ru.ifmo.backend_2021.expressions;

import java.util.Map;

public abstract class Expression {
  public abstract int evaluate(int x);
  public int evaluateWithVariables(Map<String, Integer> variables) {
    return 0;
  }
  public abstract String toMiniString();
  public abstract String toString();

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object comparable);

}