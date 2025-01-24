package expression.exceptions.expresion_exceptions;

public class NegativeGeomValue extends ExpressionEvaluateException {
    public NegativeGeomValue(int found, String expression) {
        super("Negative value of a variable for a geometric function: \"" + found + "\" in " + expression);
    }
}
