package expression.generic.parser;

import expression.generic.expressions.TripleExpression;

@FunctionalInterface
public interface TripleParser<E extends Number> {
    TripleExpression<E> parse(String expression);
}
