package expression;

public class Divide extends BinaryOperator {
    public Divide(VariableExpression operand1, VariableExpression operand2) {
        super("/", 2, operand1, operand2, false, false);
    }

    @Override
    public int calculate(int x, int y) {
        return x / y;
    }
}
