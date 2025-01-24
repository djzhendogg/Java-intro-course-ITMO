package markup;

import java.util.List;

public abstract class AbstractTextDecorator extends AbstractTextIterator {
    private final String separator;
    private final String typstStart;
    private final String typstEnd;
    private final String htmlStart;
    private final String htmlEnd;

    protected AbstractTextDecorator(
            final List<TextElement> textElements,
            final String separator,
            final String typstStart,
            final String typstEnd,
            final String htmlStart,
            final String htmlEnd
    ) {
        super(textElements);
        this.separator = separator;
        this.typstStart = typstStart;
        this.typstEnd = typstEnd;
        this.htmlStart = htmlStart;
        this.htmlEnd = htmlEnd;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(separator);
        super.toMarkdown(stringBuilder);
        stringBuilder.append(separator);
    }

    @Override
    public void toTypst(StringBuilder stringBuilder) {
        stringBuilder.append(typstStart);
        super.toTypst(stringBuilder);
        stringBuilder.append(typstEnd);
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(htmlStart);
        super.toHtml(stringBuilder);
        stringBuilder.append(htmlEnd);
    }
}
