package md2html.patterns;

import markup.Emphasis;
import markup.TextElement;

import java.util.List;

public class EmphasisStarPattern extends AbstractPattern {
    public EmphasisStarPattern() {
        super("*");
    }

    @Override
    public TextElement create(List<TextElement> textElements) {
        return new Emphasis(textElements);
    }
}
