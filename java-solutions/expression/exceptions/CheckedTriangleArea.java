package expression.exceptions;

import expression.VariableExpression;
import expression.exceptions.expresion_exceptions.NegativeGeomValue;

public class CheckedTriangleArea extends CheckedBinaryOperator {
    public CheckedTriangleArea(VariableExpression operand1, VariableExpression operand2) {
        super("◣", 2, operand1, operand2, true, true);
    }

    @Override
    protected int calculate(int x, int y) {
        if (x < 0) {
            throw new NegativeGeomValue(x, this.toString());
        } else if (y < 0) {
            throw new NegativeGeomValue(y, this.toString());
        }
        checkOverflow(x, y);
        if (y % 2 == 0 || x % 2 == 1 && y > x) {
            int z = x;
            x = y;
            y = z;
        }
        int result = x / 2 * y;
        if (x % 2 == 1 && y != 1) {
            result += 1;
        }
        return result;
    }

    @Override
    protected boolean hasOverflow(int x, int y) {
        if (x == 0 || y == 0) {
            return false;
        }
        int biggest = x > y ? x : y;
        int lowest = x <= y ? x : y;
        boolean result = OverflowConstraints.checkDivideOverflow(biggest, 2);
        if (!result) {
            result = OverflowConstraints.checkMultiplyOverflow(biggest / 2, lowest);
        }
        return result;
    }
}
