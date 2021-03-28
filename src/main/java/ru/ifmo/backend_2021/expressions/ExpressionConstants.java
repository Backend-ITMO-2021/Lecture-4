package ru.ifmo.backend_2021.expressions;

import java.util.Set;

public final class ExpressionConstants {
    public final static int OPERAND_PRIORITY = 3;
    public final static int SUB_PRIORITY = 3;
    public final static int ADD_PRIORITY = 3;
    public final static int MUL_PRIORITY = 2;
    public final static int DIV_PRIORITY = 2;
    public final static int UNARY_POS_PRIORITY = 1;
    public final static int UNARY_NEG_PRIORITY = 1;

    public final static String ADD_SYMBOL = "+";
    public final static String SUB_SYMBOL = "-";
    public final static String MUL_SYMBOL = "*";
    public final static String DIV_SYMBOL = "/";
    public final static String UNARY_POS_SYMBOL = "+";
    public final static String UNARY_NEG_SYMBOL = "-";
    public final static String OPENING_BRACKET = "(";
    public final static String CLOSING_BRACKET = ")";

    public static Set<String> getSingleSymbols(){
        return Set.of(ADD_SYMBOL, SUB_SYMBOL, MUL_SYMBOL, DIV_SYMBOL, OPENING_BRACKET, CLOSING_BRACKET);
    }
}
