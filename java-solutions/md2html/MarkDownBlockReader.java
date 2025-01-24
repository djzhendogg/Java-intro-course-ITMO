package md2html;

import markup.AbstractTextDecorator;
import markup.Header;
import markup.Paragraph;
import markup.TextElement;
import md2html.patterns.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MarkDownBlockReader {
    private final BufferedReader reader;
    private boolean closed = false;
    private final List<AbstractPattern> allPatterns = List.of(
            new StrongStarPattern(),
            new StrongUnderscorePattern(),
            new StrikeoutPattern(),
            new EmphasisStarPattern(),
            new EmphasisUnderscorePattern(),
            new CodePattern(),
            new MarkPattern()
    );
    private final Map<Character, String> replaceCharacters = new HashMap<>(
            Map.of('&' , "&amp;", '<' , "&lt;",'>' , "&gt;")
    );

    public MarkDownBlockReader(InputStream input) throws UnsupportedEncodingException {
        this.reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
    }

    public AbstractTextDecorator nextBlock() throws IOException {
        List<TextElement> rowParagraph;
        AbstractTextDecorator finalParagraph;
        boolean isHeader = false;
        int headerLevel = 0;

        String block = toEscapedString(readBlock());
        if (block.startsWith("#")) {
            while (headerLevel < block.length() && block.charAt(headerLevel) == '#') {
                headerLevel++;
            }
            if (headerLevel < block.length() && Character.isWhitespace(block.charAt(headerLevel))) {
                isHeader = true;
            }
        }

        if (isHeader) {
            rowParagraph = transformToTextElements(block.substring(headerLevel + 1));
            finalParagraph = new Header(rowParagraph, headerLevel);
        } else {
            rowParagraph = transformToTextElements(block);
            finalParagraph = new Paragraph(rowParagraph);
        }

        return finalParagraph;
    }

    public boolean hasNext() throws IOException {
        return !closed;
    }

    public void close() throws IOException {
        reader.close();
        closed = true;
    }

    private StringBuilder readBlock() throws IOException {
        boolean previosLine = false;
        StringBuilder textBlock = new StringBuilder();

        String line = reader.readLine();

        while (line != null && line.isEmpty()) {
            line = reader.readLine();
        }

        while (line != null && !line.isEmpty()) {
            if (previosLine) {
                textBlock.append(System.lineSeparator());
            }
            previosLine = true;
            textBlock.append(line);
            line = reader.readLine();
        }

        if (line == null) {
            close();
        }
        return textBlock;
    }

    private List<TextElement> transformToTextElements(String line) {
        TokenStack tokenStack = new TokenStack();
        int pointer = 0;

        for (int i = 0; i < line.length(); i++) {
            AbstractPattern currentPattern;
            if (line.charAt(i) == '\\') {
                tokenStack.addRowText(line.substring(pointer, i));
                i++;
                pointer = i;
                continue;
            }

            currentPattern = takePattern(line, i);
            if (currentPattern == null) {
                continue;
            }

            tokenStack.addRowText(line.substring(pointer, i));
            i += currentPattern.getAdditionalLen();
            pointer = i + 1;
            boolean patternNotExists = tokenStack.add(currentPattern);
            if (patternNotExists) {
                continue;
            }
            tokenStack.pushDown(currentPattern);
        }
        tokenStack.removeDummyNesting(null);
        tokenStack.addRowText(line.substring(pointer));
        return tokenStack.getFistTokenList();
    }

    private AbstractPattern takePattern(String line, int index) {
        for (AbstractPattern p : allPatterns) {
            String toCheck = line.substring(index, Math.min(line.length(), index + p.getLen()));
            if (toCheck.equals(p.getSeparator())) {
                return p;
            }
        }
        return null;
    }

    private String toEscapedString(StringBuilder str) {
        StringBuilder escapedStr = new StringBuilder();
        int start = 0;
        for (int i = 0; i < str.length(); i++) {
            if (replaceCharacters.containsKey(str.charAt(i))) {
                escapedStr.append(str, start, i);
                escapedStr.append(replaceCharacters.get(str.charAt(i)));
                start = i + 1;
            }
        }
        escapedStr.append(str, start, str.length());
        return escapedStr.toString();
    }
}
