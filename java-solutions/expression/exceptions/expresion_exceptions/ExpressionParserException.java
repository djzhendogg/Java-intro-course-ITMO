package expression.exceptions.expresion_exceptions;

public class ExpressionParserException extends ExpressionException {
    public ExpressionParserException(String message) {
        super(message);
    }

    public ExpressionParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
