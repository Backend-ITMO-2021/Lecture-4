package ru.ifmo.backend_2021;
import ru.ifmo.backend_2021.expressions.Expression;
import java.util.ArrayList;

public class ExpressionParser {

  public static Expression parse(String s) {
    ArrayList<Lex> lexes = LexAnalyze(formatString(s));
    lexes.add(new Lex(LexType.EOF, ""));
    return Lex.expr(new Lex.LexBuffer(lexes));
  }

  private static String formatString(String s){
    return s.replaceAll(" ", "");
  }

  private static ArrayList<Lex> LexAnalyze(String clearString){
    ArrayList<Lex> lexes = new ArrayList<>();
    int pointer = 0;
    while (pointer < clearString.length()) {
      char c = clearString.charAt(pointer);
      if (c == '('){ lexes.add(new Lex(LexType.LB, c)); pointer++; continue; }
      if (c == ')'){ lexes.add(new Lex(LexType.RB, c)); pointer++; continue; }
      if (c == '+'){ lexes.add(new Lex(LexType.PLUS, c)); pointer++; continue; }
      if (c == '-'){ lexes.add(new Lex(LexType.MINUS, c)); pointer++; continue; }
      if (c == '/'){ lexes.add(new Lex(LexType.DIV, c)); pointer++; continue; }
      if (c == '*'){ lexes.add(new Lex(LexType.MUL, c)); pointer++; continue; }
      if (Character.isDigit(c)){
        StringBuilder numberSb = new StringBuilder();
        do {
          numberSb.append(c);
          pointer++;
          if (pointer >= clearString.length()) {
            break;
          }
          c = clearString.charAt(pointer);
        } while (Character.isDigit(c));
        lexes.add(new Lex(LexType.NUMBER, numberSb.toString()));
        continue;
      }
      if (Character.isLetter(c)){
        StringBuilder letterSb = new StringBuilder();
        do {
          letterSb.append(c);
          pointer++;
          if (pointer >= clearString.length()) {
            break;
          }
          c = clearString.charAt(pointer);
        } while (Character.isLetter(c));
        lexes.add(new Lex(LexType.VAR, letterSb.toString()));
      }
    }
    return lexes;
  }
}
