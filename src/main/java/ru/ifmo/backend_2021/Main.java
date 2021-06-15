package ru.ifmo.backend_2021;

public class Main {
    public static void main(String[] args) {
        String testString = "(x - ((1 * 2) + x))";
        System.out.println(ExpressionParser.parse(testString).toMiniString());
    }
}
