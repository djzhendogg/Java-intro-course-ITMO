package md2html.patterns;

import markup.Emphasis;
import markup.TextElement;

import java.util.List;

public class EmphasisUnderscorePattern extends AbstractPattern {
    public EmphasisUnderscorePattern() {
        super("_");
    }

    @Override
    public TextElement create(List<TextElement> textElements) {
        return new Emphasis(textElements);
    }
}
