package expression.exceptions.expresion_exceptions;

public class ExpectAtomException extends ExpressionParserException {
    public ExpectAtomException(char found) {
        super("Variable or constant expected, '" + found + "' found");
    }
}
