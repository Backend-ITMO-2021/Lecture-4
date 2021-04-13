package ru.ifmo.backend_2021;

import java.util.ArrayList;


public class LexicalAnalyzer {
    private String s;

    public LexicalAnalyzer(String s) {
        this.s = s.trim();
    }

    public ArrayList<String> tokenize() {
        ArrayList<String> tokens = new ArrayList<>();

        for (char ch: this.s.toCharArray()) {
            if (!Character.isWhitespace(ch)) {
                tokens.add(Character.toString(ch));
            }
        }

        int cursor = 1;
        while(cursor < tokens.size()) {
            String curr = tokens.get(cursor);
            String prev = tokens.get(cursor - 1);

            if (isNumberOrVariable(curr) && isNumberOrVariable(prev)) {
                tokens.set(cursor - 1, prev.concat(curr));
                tokens.remove(cursor);
            } else {
                cursor++;
            }
        }

        return tokens;
    }

    private boolean isNumberOrVariable(String s) {
        String[] operations = {"+", "-", "*", "/", "(", ")"};

        for (String op: operations) {
            if (s.equals(op)) {
                return false;
            }
        }

        return true;
    }
}

