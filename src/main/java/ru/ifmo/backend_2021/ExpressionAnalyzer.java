package ru.ifmo.backend_2021;

import java.util.ArrayList;
import java.util.List;


enum SignType {
    OPEN, CLOSE, PLUS, MINUS, DIV, MUL, NUMBER, VAR, EOF;
}
public class ExpressionAnalyzer {
    public final SignType type;
    public final String value;

    public ExpressionAnalyzer(SignType curType, Character curValue) {
        type = curType;
        value = curValue.toString();
    }

    public ExpressionAnalyzer(SignType curType, String curValue) {
        type = curType;
        value = curValue;
    }

    public static List<ExpressionAnalyzer> analyzer(String expr) {
        ArrayList<ExpressionAnalyzer> exprList = new ArrayList<>();
        int pos = 0;

        while (pos < expr.length()) {
            char c = expr.charAt(pos);
            switch (c) {
                case '(':
                    exprList.add(new ExpressionAnalyzer(SignType.OPEN, c));
                    pos++;
                    continue;
                case ')':
                    exprList.add(new ExpressionAnalyzer(SignType.CLOSE, c));
                    pos++;
                    continue;
                case '+':
                    exprList.add(new ExpressionAnalyzer(SignType.PLUS, c));
                    pos++;
                    continue;
                case '-':
                    exprList.add(new ExpressionAnalyzer(SignType.MINUS, c));
                    pos++;
                    continue;
                case '*':
                    exprList.add(new ExpressionAnalyzer(SignType.MUL, c));
                    pos++;
                    continue;
                case '/':
                    exprList.add(new ExpressionAnalyzer(SignType.DIV, c));
                    pos++;
                    continue;
                default:
                    if (Character.isDigit(c)) {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expr.length()) {
                                break;
                            }
                            c = expr.charAt(pos);
                        } while (Character.isDigit(c));
                        exprList.add(new ExpressionAnalyzer(SignType.NUMBER, sb.toString()));
                    } else if (Character.isLetter(c)) {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expr.length()) {
                                break;
                            }
                            c = expr.charAt(pos);
                        } while (Character.isLetter(c));
                        exprList.add(new ExpressionAnalyzer(SignType.VAR, sb.toString()));
                     } else {
                        throw new RuntimeException("Unexpected character: " + c);
                    }
            }
        }
        exprList.add(new ExpressionAnalyzer(SignType.EOF, ""));

        return exprList;
    }
}