package markup;

import java.util.List;

public class Paragraph extends AbstractTextDecorator {
    public Paragraph(List<TextElement> textElements) {
        super(textElements, "", "", "", "<p>", "</p>");
    }
}
