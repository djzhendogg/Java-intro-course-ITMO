package expression.generic.expressions;

import expression.generic.operation_types.NumericOperations;

public class Negative<T extends Number> extends UnaryOperator<T> {
    public Negative(TripleExpression<T> operand, NumericOperations<T> operations) {
        super("-", 3, operand, operations);
    }

    @Override
    protected T calculate(T x) {
        return operations.negate(x);
    }
}
