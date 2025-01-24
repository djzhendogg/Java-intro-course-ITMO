package expression.exceptions.expresion_exceptions;

public class EndOfFileException extends ExpressionParserException {
    public EndOfFileException(char found) {
        super("End of expression expected, '" + found + "' found");
    }
}
