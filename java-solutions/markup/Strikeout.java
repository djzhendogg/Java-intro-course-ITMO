package markup;

import java.util.List;

public class Strikeout extends AbstractTextDecorator {
    public Strikeout(List<TextElement> textElements) {
        super(textElements, "~", "#strike[", "]", "<s>", "</s>");
    }
}
