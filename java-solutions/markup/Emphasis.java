package markup;

import java.util.List;

public final class Emphasis extends AbstractTextDecorator {
    public Emphasis(List<TextElement> textElements) {
        super(textElements, "*", "#emph[", "]", "<em>", "</em>");
    }
}
