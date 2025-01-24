package md2html.patterns;

import markup.Strong;
import markup.TextElement;

import java.util.List;

public class StrongUnderscorePattern extends AbstractPattern {
    public StrongUnderscorePattern() {
        super("__");
    }

    @Override
    public TextElement create(List<TextElement> textElements) {
        return new Strong(textElements);
    }
}
