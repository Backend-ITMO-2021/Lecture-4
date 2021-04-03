package ru.ifmo.backend_2021.expressions;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public abstract class Expression {

  protected Expression[] elements;
  protected String SYMBOL;
  protected int PRIORITY ;

  public Expression(Expression... elements){
    this.elements = elements;
  }

  abstract public int evaluate(int x);

  public String toString() {
    return "(" + elements[0].toString() + SYMBOL + elements[1].toString() + ")";
  }

  public String toMiniString() {
    StringBuilder leftPart = new StringBuilder();
    StringBuilder rightPart = new StringBuilder();
    if ((elements[0].PRIORITY < this.PRIORITY) && (elements[0].PRIORITY != 0)) {
      leftPart.append("(");
      leftPart.append(elements[0].toMiniString());
      leftPart.append(")");
    } else leftPart.append(elements[0].toMiniString());

    if ((elements[1].PRIORITY) == 0){
      rightPart.append(elements[1].toMiniString());
    } else {
      if (!(elements[1].PRIORITY < this.PRIORITY ||
              (elements[1].PRIORITY == this.PRIORITY &&
                      (((elements[1].PRIORITY > 0) && !this.SYMBOL.equals(elements[1].SYMBOL)) ||
                              (this.SYMBOL.equals(" - ") || this.SYMBOL.equals(" / ")))))) {
        rightPart.append(elements[1].toMiniString());
      } else {
        rightPart.append("(");
        rightPart.append(elements[1].toMiniString());
        rightPart.append(")");
      }
    }
    return leftPart.toString() + SYMBOL + rightPart.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Expression)) return false;
    Expression that = (Expression) o;
    return PRIORITY == that.PRIORITY && Arrays.equals(elements, that.elements) && SYMBOL.equals(that.SYMBOL);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(SYMBOL, PRIORITY);
    result = 31 * result + Arrays.hashCode(elements);
    return result;
  }

  int evaluateWithVariables(Map<String, Integer> variables){
    return -1;
  }
}
