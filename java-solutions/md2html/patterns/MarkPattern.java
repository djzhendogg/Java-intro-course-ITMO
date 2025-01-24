package md2html.patterns;

import markup.Mark;
import markup.TextElement;

import java.util.List;

public class MarkPattern extends AbstractPattern{
    public MarkPattern() {
        super("~");
    }

    @Override
    public TextElement create(List<TextElement> textElements) {
        return new Mark(textElements);
    }
}
