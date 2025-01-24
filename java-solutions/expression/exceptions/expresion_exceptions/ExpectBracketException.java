package expression.exceptions.expresion_exceptions;

public class ExpectBracketException extends ExpressionParserException {
    public ExpectBracketException(char expected, char found) {
        super("'" + expected + "' expected, '" + found + "' found");
    }
}
