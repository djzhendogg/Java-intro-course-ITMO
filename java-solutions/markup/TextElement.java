package markup;

public interface TextElement {

    void toMarkdown(StringBuilder stringBuilder);

    void toTypst(StringBuilder stringBuilder);

    void toHtml(StringBuilder stringBuilder);
}
