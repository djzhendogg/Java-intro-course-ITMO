package markup;

import java.util.List;

public final class Code extends AbstractTextDecorator {
    public Code(List<TextElement> textElements) {
        super(textElements, "`", "`", "`", "<code>", "</code>");
    }
}
