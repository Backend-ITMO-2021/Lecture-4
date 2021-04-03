package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.Expression;

import java.util.LinkedList;

public class ExpressionParser {
  public static Expression parse(String s) {
    LinkedList<Token> tokenLinkedList = Parser.tokenize(s);

    return Parser.parse(tokenLinkedList);
  }


  public static void main(String[] args) {
    String s1 = "10";
    String s2 = "( (( x)  ))";
    String s3 = "( 2-( (( x)  )))";
    String s4 = "( ( (( x)  ))+( (( x)  )))  ";
    String s5 = " ( (( x)  ))/-2  "; // 5
    String s6 = "( ( 1-2)  -3)  ";
    LinkedList<Token> tokenLinkedList = Parser.tokenize(s6);
    for (Token token: tokenLinkedList) {
      System.out.println(token.type);
    }
    System.out.println(Parser.parse(tokenLinkedList).evaluate(3));
  }
}
