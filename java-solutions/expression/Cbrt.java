package expression;

public class Cbrt extends UnaryOperator{
    public Cbrt(VariableExpression operand) {
        super("∛", 3, operand);
    }

    @Override
    public int calculate(int x) {
        return (int) Math.cbrt(x);
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        if (needBrackets()) {
            sb.append("(");
        }
        sb.append(operand.toMiniString());
        if (needBrackets()) {
            sb.append(")");
        }
        return sb.toString();
    }
}
