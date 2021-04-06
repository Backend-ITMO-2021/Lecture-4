package ru.ifmo.backend_2021;
import java.util.List;
import java.util.Arrays;

public class Constants {
    public final static String ADD_SYMBOL = "+";
    public final static String SUB_SYMBOL = "-";
    public final static char SUB_CHAR = '-';
    public final static String MUL_SYMBOL = "*";
    public final static String DIV_SYMBOL = "/";
    public final static String OPENING_BRACKET = "(";
    public final static String CLOSING_BRACKET = ")";
    public final static String REGEX_STRING = "(\\w*(?<![^/\\-+*(])-\\d+)|(\\d+)|(\\w*(?<![^/\\-+*(])-[a-zA-Z]+)|([a-zA-Z]+)|[/\\-+*()]";
    public final static List<String> OPERATORS = Arrays.asList(ADD_SYMBOL, SUB_SYMBOL, MUL_SYMBOL, DIV_SYMBOL);
}
