package md2html.patterns;

import markup.Code;
import markup.TextElement;

import java.util.List;

public class CodePattern extends AbstractPattern{
    public CodePattern() {
        super("`");
    }

    @Override
    public TextElement create(List<TextElement> textElements) {
        return new Code(textElements);
    }
}
