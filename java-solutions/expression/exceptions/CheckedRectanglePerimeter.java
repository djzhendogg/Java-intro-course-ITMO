package expression.exceptions;

import expression.VariableExpression;
import expression.exceptions.expresion_exceptions.NegativeGeomValue;

public class CheckedRectanglePerimeter extends CheckedBinaryOperator {
    public CheckedRectanglePerimeter(VariableExpression operand1, VariableExpression operand2) {
        super("▯", 2, operand1, operand2, true, true);
    }

    @Override
    protected int calculate(int x, int y) {
        if (x < 0) {
            throw new NegativeGeomValue(x, this.toString());
        } else if (y < 0) {
            throw new NegativeGeomValue(y, this.toString());
        }
        checkOverflow(x, y);
        return (x + y) * 2;
    }

    @Override
    protected boolean hasOverflow(int left, int right) {
        if (
                OverflowConstraints.checkMultiplyOverflow(left, 2) ||
                OverflowConstraints.checkMultiplyOverflow(right, 2)
        ) {
            return true;
        }
        return OverflowConstraints.checkAddOverflow(left * 2, right * 2);
    }
}
