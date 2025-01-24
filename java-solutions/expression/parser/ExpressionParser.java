package expression.parser;

import expression.*;
import expression.exceptions.expresion_exceptions.ExpectAtomException;

public class ExpressionParser extends BaseParser implements TripleParser {
    @Override
    public VariableExpression parse(String expression) {
        source = new StringSource(expression);
        take();
        return parseAdditiveExpression();
    }

    private VariableExpression parseAdditiveExpression() {
        VariableExpression primaryLeft = parseMultiplicativeExpression();

        while (true) {
            skipWhitespace();
            if (take('+')) {
                primaryLeft = new Add(primaryLeft, parseMultiplicativeExpression());
            } else if (take('-')) {
                primaryLeft = new Subtract(primaryLeft, parseMultiplicativeExpression());
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
                primaryLeft = new Multiply(primaryLeft, parsePrimary());
            } else if (take('/')) {
                primaryLeft = new Divide(primaryLeft, parsePrimary());
            } else {
                return primaryLeft;
            }
        }
    }

    private VariableExpression parsePrimary() {
        skipWhitespace();
        if (take('(')) {
            VariableExpression res = parseAdditiveExpression();
            skipWhitespace();
            expect(')');
            return res;
        } else if (take('-')) {
            if (Character.isDigit(get())) {
                return parseConst(true);
            } else {
                return parseUnaryMinus();
            }
        } else if (take('∛')) {
            return parseCbrt();
        } else {
            return parseAtom();
        }
    }

    private VariableExpression parseUnaryMinus() {
        return new UnaryMinus(parsePrimary());
    }

    private VariableExpression parseCbrt() {
        return new Cbrt(parsePrimary());
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
        return new Variable(sb.toString());
    }

    private VariableExpression parseConst(boolean isNegative) {
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append('-');
        }
        while (Character.isDigit(get())) {
            sb.append(take());
        }
        return new Const(Integer.parseInt(sb.toString()));
    }
}
