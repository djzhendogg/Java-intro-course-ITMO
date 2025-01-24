package markup;

import java.util.List;

public class Header extends AbstractTextDecorator {
    public Header(List<TextElement> textElements, int level) {
        super(textElements, "", "", "", "<h" + level + ">", "</h" + level + ">");
    }
}

