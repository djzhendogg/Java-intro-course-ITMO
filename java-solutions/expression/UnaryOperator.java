package expression;

import java.util.Objects;

public abstract class UnaryOperator implements VariableExpression {
    protected final String value;
    private final int priority;
    protected final VariableExpression operand;

    public UnaryOperator(String value, int priority, VariableExpression operand) {
        this.value = value;
        this.priority = priority;
        this.operand = operand;
    }

    @Override
    public int evaluate(int x) {
        return calculate(operand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
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
        UnaryOperator that = (UnaryOperator) o;
        return priority == that.priority
                && Objects.equals(value, that.value)
                && operand.equals(that.operand);
    }

    @Override
    public int hashCode() {
        int operandHash = (operand == null) ? 0 : operand.hashCode();
        return ((((17 + value.hashCode()) * 31 + priority) * 23) + operandHash) * 41;
    }

    protected abstract int calculate(int x);

    protected boolean needBrackets() {
        if (operand instanceof BinaryOperator op) {
            return op.getPriority() < priority;
        }
        return false;
    }
}
