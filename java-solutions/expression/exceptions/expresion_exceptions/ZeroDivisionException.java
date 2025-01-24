package expression.exceptions.expresion_exceptions;

public class ZeroDivisionException extends ExpressionEvaluateException {
    public ZeroDivisionException(String expression) {
        super("Division by zero: " + expression);
    }
}
