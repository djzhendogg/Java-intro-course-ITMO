package md2html.patterns;

import markup.Strong;
import markup.TextElement;

import java.util.List;

public class StrongStarPattern extends AbstractPattern {
    public StrongStarPattern() {
        super("**");
    }

    @Override
    public TextElement create(List<TextElement> textElements) {
        return new Strong(textElements);
    }
}
