package expression.exceptions.expresion_exceptions;

public class CommandLineArgumentsException extends RuntimeException {
    public CommandLineArgumentsException(String pattern) {
        super("The required arguments are missing. Command line argument pattern: " + pattern);
    }
}
