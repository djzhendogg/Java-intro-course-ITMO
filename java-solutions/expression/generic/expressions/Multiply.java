package expression.generic.expressions;

import expression.generic.operation_types.NumericOperations;

public class Multiply<T extends Number> extends BinaryOperator<T> {
    public Multiply(TripleExpression<T> operand1, TripleExpression<T> operand2, NumericOperations<T> operations) {
        super("*", 2, operand1, operand2, true, true, operations);
    }

    @Override
    protected T calculate(T left, T right) {
        return operations.multiply(left, right);
    }
}
