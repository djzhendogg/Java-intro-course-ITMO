package expression.generic.parser;

import expression.exceptions.expresion_exceptions.*;
import expression.exceptions.BaseParser;
import expression.generic.expressions.*;
import expression.generic.operation_types.NumericOperations;
import expression.parser.StringSource;

import java.util.Map;

public class ExpressionParser<T extends Number> extends BaseParser implements TripleParser<T> {
    private static final Map<Character, Character> BRACKETS_PAIRS = Map.of(
            '(', ')', '{', '}', '[', ']'
    );
    private final NumericOperations<T> operations;

    public ExpressionParser(NumericOperations<T> operations) {
        this.operations = operations;
    }

    @Override
    public TripleExpression<T> parse(String expression) {
        source = new StringSource(expression);
        take();
        TripleExpression<T> result = parseAdditiveExpression();
        expectEnd();
        return result;
    }

    private TripleExpression<T> parseAdditiveExpression() {
        TripleExpression<T> primaryLeft = parseMultiplicativeExpression();

        while (true) {
            skipWhitespace();
            if (take('+')) {
                primaryLeft = new Add<>(primaryLeft, parseMultiplicativeExpression(), operations);
            } else if (take('-')) {
                primaryLeft = new Subtract<>(primaryLeft, parseMultiplicativeExpression(), operations);
            } else {
                return primaryLeft;
            }
        }
    }

    private TripleExpression<T> parseMultiplicativeExpression() {
        TripleExpression<T> primaryLeft = parsePrimary();

        while (true) {
            skipWhitespace();
            if (take('*')) {
                primaryLeft = new Multiply<>(primaryLeft, parsePrimary(), operations);
            } else if (take('/')) {
                primaryLeft = new Divide<>(primaryLeft, parsePrimary(), operations);
            } else {
                return primaryLeft;
            }
        }
    }

    private TripleExpression<T> parsePrimary() {
        skipWhitespace();
        if (test('(') || test('{') || test('[')) {
            char startBracket = take();
            TripleExpression<T> res = parseAdditiveExpression();
            skipWhitespace();
            if (!test(BRACKETS_PAIRS.get(startBracket))) {
                throw new ExpectBracketException(BRACKETS_PAIRS.get(startBracket), get());
            }
            take();
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

    private TripleExpression<T> parseUnaryMinus() {
        return new Negative<>(parsePrimary(), operations);
    }

    private TripleExpression<T> parseAtom() {
        skipWhitespace();
        if (Character.isDigit(get())) {
            return parseConst(false);
        } else if (Character.isLetter(get())) {
            return parseVariable();
        } else {
            throw new ExpectAtomException(take());
        }
    }

    private TripleExpression<T> parseVariable() {
        StringBuilder sb = new StringBuilder();
        while (Character.isLetterOrDigit(get())) {
            sb.append(take());
        }
        String result = sb.toString();
        if (!isValidVariable(result)) {
            throw new UnknownVariableException(result);
        }
        return new Variable<>(result);
    }

    private TripleExpression<T> parseConst(boolean isNegative) {
        StringBuilder sb = new StringBuilder();
        T result;
        if (isNegative) {
            sb.append('-');
        }
        while (Character.isDigit(get())) {
            sb.append(take());
        }
        try {
            result = operations.cast(sb.toString());
        } catch (NumberFormatException e) {
            throw new ConstValueException(sb.toString(), e);
        }
        return new Const<>(result);
    }

    private void expectEnd() {
        if (!take(END)) {
            throw new EndOfFileException(get());
        }
    }

    private boolean isValidVariable(String value) {
        return value.endsWith("x") || value.endsWith("z") || value.endsWith("y");
    }
}
