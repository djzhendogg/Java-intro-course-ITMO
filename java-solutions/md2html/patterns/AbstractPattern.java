package md2html.patterns;

import markup.TextElement;

import java.util.List;

public abstract class AbstractPattern {
    private final String separator;

    protected AbstractPattern(String separator) {
        this.separator = separator;
    }

    public int getAdditionalLen() {
        return getSeparator().length() - 1;
    }

    public int getLen() {
        return getSeparator().length();
    }

    public String getSeparator() {
        return separator;
    }

    public abstract TextElement create(List<TextElement> textElements);
}
