package markup;

import java.util.List;

public abstract class AbstractTextIterator implements TextElement {
    List<TextElement> textElements;

    protected AbstractTextIterator(List<TextElement> textElements) {
        this.textElements = textElements;
    }

    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        for (TextElement textElement : textElements) {
            textElement.toMarkdown(stringBuilder);
        }
    }

    @Override
    public void toTypst(StringBuilder stringBuilder) {
        for (TextElement textElement : textElements) {
            textElement.toTypst(stringBuilder);
        }
    }

    @Override
    public void toHtml(StringBuilder stringBuilder) {
        for (TextElement textElement : textElements) {
            textElement.toHtml(stringBuilder);
        }
    }
}
