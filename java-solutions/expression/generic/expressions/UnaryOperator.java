package expression.generic.expressions;

import expression.generic.operation_types.NumericOperations;

import java.util.Objects;

public abstract class UnaryOperator<T extends Number> implements TripleExpression<T> {
    protected final String value;
    private final int priority;
    protected final TripleExpression<T> operand;
    protected final NumericOperations<T> operations;

    public UnaryOperator(String value, int priority, TripleExpression<T> operand, NumericOperations<T> operations) {
        this.value = value;
        this.priority = priority;
        this.operand = operand;
        this.operations = operations;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(operand.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return value + "(" + operand.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnaryOperator<?> that = (UnaryOperator<?>) o;
        return priority == that.priority
                && Objects.equals(value, that.value)
                && operand.equals(that.operand);
    }

    @Override
    public int hashCode() {
        int operandHash = (operand == null) ? 0 : operand.hashCode();
        return ((((17 + value.hashCode()) * 31 + priority) * 23) + operandHash) * 41;
    }

    protected abstract T calculate(T x);

    protected boolean needBrackets() {
        if (operand instanceof BinaryOperator<?> op) {
            return op.getPriority() < priority;
        }
        return false;
    }
}
