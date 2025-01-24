package expression.generic.expressions;

import expression.generic.operation_types.NumericOperations;

public class Divide<T extends Number> extends BinaryOperator<T> {
    public Divide(TripleExpression<T> operand1, TripleExpression<T> operand2, NumericOperations<T> operations) {
        super("/", 2, operand1, operand2, false, false, operations);
    }

    @Override
    protected T calculate(T left, T right) {
        return operations.divide(left, right);
    }
}
