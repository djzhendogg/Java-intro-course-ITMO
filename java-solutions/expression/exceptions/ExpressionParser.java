package expression.exceptions;

import expression.Const;
import expression.Variable;
import expression.VariableExpression;
import expression.exceptions.expresion_exceptions.*;
import expression.parser.StringSource;


public class ExpressionParser extends BaseParser implements TripleParser {
    @Override
    public VariableExpression parse(String expression) {
        source = new StringSource(expression);
        take();
        VariableExpression result = parseGeometricExpression();
        expectEnd();
        return result;
    }

    private VariableExpression parseGeometricExpression() {
        VariableExpression primaryLeft = parseAdditiveExpression();

        while (true) {
            skipWhitespace();
            if (take('▯')) {
                primaryLeft = new CheckedRectanglePerimeter(primaryLeft, parseAdditiveExpression());
            } else if (take('◣')) {
                primaryLeft = new CheckedTriangleArea(primaryLeft, parseAdditiveExpression());
            } else {
                return primaryLeft;
            }
        }
    }

    private VariableExpression parseAdditiveExpression() {
        VariableExpression primaryLeft = parseMultiplicativeExpression();

        while (true) {
            skipWhitespace();
            if (take('+')) {
                primaryLeft = new CheckedAdd(primaryLeft, parseMultiplicativeExpression());
            } else if (take('-')) {
                primaryLeft = new CheckedSubtract(primaryLeft, parseMultiplicativeExpression());
            } else {
                return primaryLeft;
            }
        }
    }

    private VariableExpression parseMultiplicativeExpression() {
        VariableExpression primaryLeft = parsePrimary();

        while (true) {
            skipWhitespace();
            if (take('*')) {
                primaryLeft = new CheckedMultiply(primaryLeft, parsePrimary());
            } else if (take('/')) {
                primaryLeft = new CheckedDivide(primaryLeft, parsePrimary());
            } else {
                return primaryLeft;
            }
        }
    }

    private VariableExpression parsePrimary() {
        skipWhitespace();
        if (take('(')) {
            VariableExpression res = parseGeometricExpression();
            skipWhitespace();
            expectBracket();
            return res;
        } else if (take('-')) {
            if (Character.isDigit(get())) {
                return parseConst(true);
            } else {
                return parseUnaryMinus();
            }
        } else {
            return parseAtom();
        }
    }

    private VariableExpression parseUnaryMinus() {
        return new CheckedNegate(parsePrimary());
    }

    private VariableExpression parseAtom() {
        skipWhitespace();
        if (Character.isDigit(get())) {
            return parseConst(false);
        } else if (Character.isLetter(get())) {
            return parseVariable();
        } else {
            throw new ExpectAtomException(take());
        }
    }

    private VariableExpression parseVariable() {
        StringBuilder sb = new StringBuilder();
        while (Character.isLetterOrDigit(get())) {
            sb.append(take());
        }
        String result = sb.toString();
        if (!isValidVariable(result)) {
            throw new UnknownVariableException(result);
        }
        return new Variable(result);
    }

    private VariableExpression parseConst(boolean isNegative) {
        StringBuilder sb = new StringBuilder();
        int result;
        if (isNegative) {
            sb.append('-');
        }
        while (Character.isDigit(get())) {
            sb.append(take());
        }
        try {
            result = Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            throw new ConstValueException(sb.toString(), e);
        }
        return new Const(result);
    }

    private void expectEnd() {
        if (!take(END)) {
            throw new EndOfFileException(get());
        }
    }

    private void expectBracket() {
        if (!take(')')) {
            throw new ExpectBracketException(')', get());
        }
    }

    private boolean isValidVariable(String value) {
        return value.endsWith("x") || value.endsWith("z") || value.endsWith("y");
    }
}
