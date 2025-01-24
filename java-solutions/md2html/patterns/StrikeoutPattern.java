package md2html.patterns;

import markup.Strikeout;
import markup.TextElement;

import java.util.List;

public class StrikeoutPattern extends AbstractPattern{
    public StrikeoutPattern() {
        super("--");
    }

    @Override
    public TextElement create(List<TextElement> textElements) {
        return new Strikeout(textElements);
    }
}
