package ru.ifmo.backend_2021;
import ru.ifmo.backend_2021.expressions.*;

public class TryTest {
    public static void main(String[] args) {
        String exp = new Subtract(
            new Multiply(
              new Const(2),
              new Variable("x")
            ),
            new Const(3)
          ).toMiniString();

        // System.out.println(expressionA.evaluateWithVariables(Map.of("x", 1, "y", 10)));
        System.out.println(exp);
    }
}
