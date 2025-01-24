package md2html;

import markup.TextElement;
import md2html.patterns.AbstractPattern;

import java.util.ArrayList;
import java.util.List;

public class TextBlock {
    private final List<TextElement> textElements = new ArrayList<>();
    private final AbstractPattern pattern;

    public TextBlock(AbstractPattern pattern) {
        this.pattern = pattern;
    }

    public AbstractPattern getPattern() {
        return pattern;
    }

    public void addElement(TextElement el) {
        textElements.add(el);
    }

    public void addElements(List<TextElement> elements) {
        textElements.addAll(elements);
    }

    public int getLength() {
        return textElements.size();
    }

    public List<TextElement> getTextElementList() {
        return textElements;
    }
}
