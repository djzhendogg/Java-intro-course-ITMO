package expression.exceptions.expresion_exceptions;

public class ConstValueException extends ExpressionParserException {
    public ConstValueException(String found, Throwable cause) {
        super("\"" + found + "\" can not be parsed to int", cause);
    }
}
