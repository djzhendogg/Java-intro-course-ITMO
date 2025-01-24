package markup;

import java.util.List;

public class Mark extends AbstractTextDecorator{
    public Mark(List<TextElement> textElements) {
        super(textElements, "~", "~", "~", "<mark>", "</mark>");
    }

}
