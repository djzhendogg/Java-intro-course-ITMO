package expression.exceptions;

import expression.parser.CharSource;

public abstract class BaseParser {
    protected static final char END = '\0';
    protected CharSource source;
    private char ch = 0xffff;

    protected char get() {
        return ch;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }
}
