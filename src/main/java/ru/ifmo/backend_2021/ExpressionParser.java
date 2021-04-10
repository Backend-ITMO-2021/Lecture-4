package ru.ifmo.backend_2021;

import ru.ifmo.backend_2021.expressions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpressionParser {

  public static Expression parse(String s) {
    ExpressionParser ep = new ExpressionParser(s);
    ep.prepare();
    return ep.listToExpressions();
  }

  private List<String> listStr;
  private List<Expression> listExp;
  private String text;
  private Expression expression;

  public ExpressionParser(String text){
    text = text.replaceAll(" ", "");
    this.text = removeBrackets(text);
    this.listStr = textToList(this.text);
    this.listExp = new ArrayList<Expression>();
  }

  public ExpressionParser(List<String> listStr, List<Expression> listExp){
    this.listStr = listStr;
    this.listExp = listExp;
  }

  private ArrayList textToList(String text) {
    ArrayList<String> listStr= new ArrayList<>();
    boolean previousLetter = false;
    boolean previousNumber = false;
    for(int i =0; i<text.length(); i++){
      if (previousLetter && Character.isLetter(text.charAt(i))){
        listStr.set(listStr.size()-1, listStr.get(listStr.size()-1) + Character.toString(text.charAt(i)));
      }else if(previousNumber && Character.isDigit(text.charAt(i))){
        listStr.set(listStr.size()-1, listStr.get(listStr.size()-1) + Character.toString(text.charAt(i)));
      }else if(Character.isLetter(text.charAt(i))){
        previousLetter = true;
        previousNumber = false;
        listStr.add(Character.toString(text.charAt(i)));
      }else if(Character.isDigit(text.charAt(i))){
        previousNumber = true;
        previousLetter = false;
        listStr.add(Character.toString(text.charAt(i)));
      }else{
        previousLetter = false;
        previousNumber = false;
        listStr.add(Character.toString(text.charAt(i)));
      }
    }
    return listStr;
  }

  private void removeMinuses(){
    if (listStr.get(0).equals("-")){
      listStr.remove(0);
      listStr.set(0, "-" + listStr.get(0));
    }
    int i = 1;
    while(i < listStr.size()){
      char prev = listStr.get(i-1).charAt(listStr.get(i-1).length()-1);
      if (listStr.get(i).equals("-") && !(Character.isLetter(prev) || Character.isDigit(prev) || prev == ')')){
        listStr.remove(i);
        listStr.set(i, "-" + listStr.get(i));
      }
      i++;
    }
  }

  private void prepare() {
    removeMinuses();
    strToExc();
  }

  public Expression listToExpressions(){
    while (listStr.contains(")")){
      parseBracket();
    }
    int i = 0;
    while(listExp.size() > 1){
      int iMultiply = listStr.indexOf("*");
      int iDivide = listStr.indexOf("/");
      int iAdd = listStr.indexOf("+");
      int iSubtract = listStr.indexOf("-");
      if (Integer.min(iMultiply, iDivide) != -1){
        parseDelimiter(Integer.min(iMultiply, iDivide));
      }else if (Integer.max(iMultiply, iDivide) != -1){
        parseDelimiter(Integer.max(iMultiply, iDivide));
      }else if(Integer.min(iAdd, iSubtract) != -1){
        parseDelimiter(Integer.min(iAdd, iSubtract));
      }else if(Integer.max(iAdd, iSubtract) != -1){
        parseDelimiter(Integer.max(iAdd, iSubtract));
      }else{
        break;
      }
    }
    return listExp.get(0);
  }

  public void strToExc(){
    for (String s : listStr){
      if(Character.isDigit(s.charAt(s.length()-1))){
        listExp.add(new Const(Integer.parseInt(s)));
      }else if (Character.isLetter(s.charAt(s.length()-1))) {
        listExp.add(new Variable(s));
      }else{
        listExp.add(null);
      }
    }
  }

  void parseBracket(){
    int end = listStr.indexOf(")");
    int start = listStr.subList(0, end).lastIndexOf("(");

    if(end-start <= 2){
      listStr.remove(end);
      listStr.remove(start);
      listExp.remove(end);
      listExp.remove(start);
    }else {
      List<String> sublistStr = new ArrayList<>(listStr.subList(start + 1, end));
      List<Expression> sublistExc = new ArrayList<>(listExp.subList(start + 1, end));
      ExpressionParser ep = new ExpressionParser(sublistStr, sublistExc);
      Expression bracketResult = ep.listToExpressions();
      listStr.set(end, null);
      listExp.set(end, bracketResult);
      listStr.subList(start, end).clear();
      listExp.subList(start, end).clear();
    }

  }

  void parseDelimiter(int i){
    String delimiter = listStr.get(i);
    if (delimiter.equals("+")){
      listExp.set(i, new Add(listExp.get(i-1), listExp.get(i+1)));
    }else if (delimiter.equals("-")){
      listExp.set(i, new Subtract(listExp.get(i-1), listExp.get(i+1)));
    }else if (delimiter.equals("*")){
      listExp.set(i, new Multiply(listExp.get(i-1), listExp.get(i+1)));
    }else if (delimiter.equals("/")){
      listExp.set(i, new Divide(listExp.get(i-1), listExp.get(i+1)));
    }
    listExp.remove(i-1);
    listExp.remove(i);

    listStr.set(i, "\\");
    listStr.remove(i-1);
    listStr.remove(i);

  }

  private String removeBrackets(String s){
    if (s.length() == 1){
      return s;
    }
    ArrayList<Integer> brackets = new ArrayList<>();
    int current = 0;
    for(int i = 0; i < s.length()-1; i++){
      if (s.charAt(i) == '(') {
        current++;
      }else if (s.charAt(i) == ')') {
        current--;
      }
      brackets.add(current);
    }
    if( Collections.min(brackets) > 0){
      s = removeBrackets(new String(s.substring(1, s.length()-1)));
    }
    return s;
  }
}
