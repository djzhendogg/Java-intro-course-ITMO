package expression.exceptions.expresion_exceptions;

public class IntOverflowException extends ExpressionEvaluateException {
    public IntOverflowException(String expression) {
        super(expression + " has overflow");
    }
}
