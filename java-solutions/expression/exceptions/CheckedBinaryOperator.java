package expression.exceptions;

import expression.BinaryOperator;
import expression.VariableExpression;
import expression.exceptions.expresion_exceptions.IntOverflowException;

public abstract class CheckedBinaryOperator extends BinaryOperator {
    public CheckedBinaryOperator(
            String value,
            int priority,
            VariableExpression operand1,
            VariableExpression operand2,
            boolean isCommutative,
            boolean isAssociative
    ) {
        super(value, priority, operand1, operand2, isCommutative, isAssociative);
    }

    protected abstract boolean hasOverflow(int x, int y);

    protected void checkOverflow(int x, int y) {
        if (hasOverflow(x, y)) {
            throw new IntOverflowException(this.toString());
        }
    }
}
