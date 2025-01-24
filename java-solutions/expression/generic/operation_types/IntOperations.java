package expression.generic.operation_types;

import expression.exceptions.expresion_exceptions.ConstValueException;
import expression.exceptions.expresion_exceptions.IntOverflowException;
import expression.exceptions.expresion_exceptions.ZeroDivisionException;
import expression.generic.expressions.IntOverflowConstraints;

public class IntOperations implements NumericOperations<Integer> {
    @Override
    public Integer add(Integer left, Integer right) {
        validateOverflow(IntOverflowConstraints.checkAddOverflow(left, right));
        return left + right;
    }

    @Override
    public Integer substarct(Integer left, Integer right) {
        validateOverflow(IntOverflowConstraints.checkSubtractOverflow(left, right));
        return left - right;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        validateOverflow(IntOverflowConstraints.checkMultiplyOverflow(left, right));
        return left * right;
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        if (right == 0) {
            throw new ZeroDivisionException(this.toString());
        }
        validateOverflow(IntOverflowConstraints.checkDivideOverflow(left, right));
        return left / right;
    }

    @Override
    public Integer negate(Integer x) {
        validateOverflow(IntOverflowConstraints.checkNegativeOverflow(x));
        return -x;
    }

    @Override
    public Integer cast(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ConstValueException(value, e);
        }
    }

    private void validateOverflow(boolean constraint) {
        if (constraint) {
            throw new IntOverflowException(this.toString());
        }
    }
}
