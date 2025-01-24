package expression;

import java.util.Objects;

public abstract class BinaryOperator implements VariableExpression {
    private final String value;
    private final int priority;
    private final VariableExpression operand1;
    private final VariableExpression operand2;
    private final boolean isCommutative;
    private final boolean isAssociative;

    public BinaryOperator(
            String value,
            int priority,
            VariableExpression operand1,
            VariableExpression operand2,
            boolean isCommutative,
            boolean isAssociative
    ) {
        this.value = value;
        this.priority = priority;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.isCommutative = isCommutative;
        this.isAssociative = isAssociative;
    }

    @Override
    public int evaluate(int x) {
        return calculate(operand1.evaluate(x), operand2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(operand1.evaluate(x, y, z), operand2.evaluate(x, y, z));
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + " " + value + " " + operand2.toString() + ")";
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (needBracketsLeft()) {
            sb.append("(");
        }
        sb.append(operand1.toMiniString());
        if (needBracketsLeft()) {
            sb.append(")");
        }
        sb.append(" ");
        sb.append(value);
        sb.append(" ");

        if (needBracketsRight()) {
            sb.append("(");
        }
        sb.append(operand2.toMiniString());
        if (needBracketsRight()) {
            sb.append(")");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryOperator that = (BinaryOperator) o;
        return priority == that.priority
                && Objects.equals(value, that.value)
                && operand1.equals(that.operand1)
                && operand2.equals(that.operand2);
    }

    @Override
    public int hashCode() {
        int operand1Hash = (operand1 == null) ? 0 : operand1.hashCode();
        int operand2Hash = (operand2 == null) ? 0 : operand2.hashCode();
        return (((((17 + value.hashCode()) * 31 + priority) * 23) + operand1Hash) * 41 + operand2Hash) * 59;
    }

    protected abstract int calculate(int x, int y);

    private boolean needBracketsRight() {
        if (operand2 instanceof BinaryOperator operand) {
            return operand.getPriority() < priority
                    || operand.getPriority() == priority
                    && (!operand.isAssociative || !isCommutative);
        }
        return false;
    }
    private boolean needBracketsLeft() {
        if (operand1 instanceof BinaryOperator operand) {
            return operand.getPriority() < priority;
        }
        return false;
    }
}
