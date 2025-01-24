package expression.generic.expressions;

import expression.generic.operation_types.NumericOperations;

public class Subtract<T extends Number> extends BinaryOperator<T> {
    public Subtract(TripleExpression<T> operand1, TripleExpression<T> operand2, NumericOperations<T> operations) {
        super("-", 1, operand1, operand2, false, true, operations);
    }

    @Override
    protected T calculate(T left, T right) {
        return operations.substarct(left, right);
    }
}
