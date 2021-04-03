package ru.ifmo.backend_2021.expressions;

public class HighPriorityExpression extends BinaryExpression {
    HighPriorityExpression(Expression v1, Expression v2, char sign) {
        super(v1, v2, sign);
    }

    @Override
    public String toMiniString() {
        boolean isFirstAddOrSubtract = firstVariable instanceof Add
                || firstVariable instanceof Subtract;
        boolean isSecondAddOrSubtract = secondVariable instanceof Add
                || secondVariable instanceof Subtract;
        String format;
        if (isFirstAddOrSubtract && !isSecondAddOrSubtract && !(secondVariable instanceof Divide)) {
            format = "(%s) %s %s";
        } else if (!isFirstAddOrSubtract && (isSecondAddOrSubtract || secondVariable instanceof Divide)) {
            format = "%s %s (%s)";
        } else if (isFirstAddOrSubtract) {
            format = "(%s) %s (%s)";
        } else {
            format = "%s %s %s";
        }
        return String.format(format, firstVariable.toMiniString(), sign, secondVariable.toMiniString());

    }
}
