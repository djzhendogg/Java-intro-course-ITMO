package expression.exceptions;

import expression.VariableExpression;

public class CheckedSubtract extends CheckedBinaryOperator {
    public CheckedSubtract(VariableExpression operand1, VariableExpression operand2) {
        super("-", 1, operand1, operand2, false, true);
    }

    @Override
    protected int calculate(int x, int y) {
        checkOverflow(x, y);
        return x - y;
    }

    @Override
    protected boolean hasOverflow(int left, int right) {
        return OverflowConstraints.checkSubtractOverflow(left, right);
    }
}
