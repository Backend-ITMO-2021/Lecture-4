package ru.ifmo.backend_2021;

import java.util.List;

public class ExpressionNavigator {
    private int pos;

    public List<ExpressionAnalyzer> exprList;

    public ExpressionNavigator(List<ExpressionAnalyzer> lexemes) {
        this.exprList = lexemes;
    }

    public ExpressionAnalyzer next() {
        return exprList.get(pos++);
    }

    public void back() {
        pos--;
    }

    public int getPos() {
        return pos;
    }
}
