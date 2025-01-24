package expression;

public class Multiply extends BinaryOperator {
    public Multiply(VariableExpression operand1, VariableExpression operand2) {
        super("*", 2, operand1, operand2, true, true);
    }

    @Override
    public int calculate(int x, int y) {
        return x * y;
    }
}
