import java.io.*;
import java.nio.charset.StandardCharsets;

public class Scanner {
    private static final int BUFFER_SIZE = 2048;
    private final BufferedReader reader;
    private char[] buffer = new char[BUFFER_SIZE];
    private int lookupPointer = 0;
    private int readCharNum;
    private Cache cache = null;

    public Scanner(InputStream input) throws UnsupportedEncodingException {
        this.reader = new BufferedReader(
                new InputStreamReader(
                        input,
                        "UTF8"
                )
        );
    }

    public Scanner(String input) throws UnsupportedEncodingException {
        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        this.reader = new BufferedReader(
                new InputStreamReader(
                        stream,
                        "UTF8"
                )
        );
    }

    public String nextWord() throws IOException {
        return nextItem(false, Pattern.WORD);
    }

    public boolean hasNextWord() throws IOException {
        return !nextItem(true, Pattern.WORD).isEmpty();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextItem(false, Pattern.INTEGER));
    }

    public boolean hasNextInt() throws IOException {
        return isInteger(nextItem(true, Pattern.INTEGER));
    }

    public long nextLong() throws IOException {
        return Long.parseLong(nextItem(false, Pattern.INTEGER));
    }

    public boolean hasNexLong() throws IOException {
        return hasNextInt();
    }

    public boolean hasNext() throws IOException {
        readInBufferIfEmpty();
        return readCharNum > lookupPointer;
    }

    public String nextLine() throws IOException {
        StringBuilder line = new StringBuilder();
        while (hasNext()) {
            char c = nextChar();
            if (c == '\r') {
                if (hasNext() && buffer[lookupPointer] == '\n') {
                    lookupPointer++;
                }
                break;
            } else if (c == '\n') {
                break;
            }
            line.append(c);
        }
        return line.toString();
    }

    public void close() throws IOException {
        reader.close();
    }

    private String nextItem(boolean isLookup, Pattern pattern) throws IOException {
        if (cache != null && cache.pattern() == pattern) {
            String item = cache.value();
            if (!isLookup) {
                lookupPointer = cache.cachePointer();
                cache = null;
            }
            return item;
        }
        StringBuilder itemSb = new StringBuilder();
        boolean isPreviousSpace = true;
        while (hasNext()) {
            char c = nextChar();
            if (!isSplitChar(c, pattern)) {
                if (isPreviousSpace) {
                    isPreviousSpace = false;
                }
                itemSb.append(c);
            } else if (!isPreviousSpace){
                break;
            }
        }
        if (itemSb.isEmpty()) {
            return "";
        }
        if (isLookup) {
            cache = new Cache(pattern, itemSb.toString(), lookupPointer);
            lookupPointer -= itemSb.length();
        }
        return itemSb.toString();
    }

    private char nextChar() throws IOException {
        readInBufferIfEmpty();
        return buffer[lookupPointer++];
    }

    private void readInBufferIfEmpty() throws IOException {
        if (lookupPointer >= readCharNum) {
            readCharNum = reader.read(buffer);
            lookupPointer = 0;
        }
    }

    private boolean isInteger(String s) {
        if (s.isEmpty()) {
            return false;
        }
        int startIndex = 0;
        if (s.charAt(0) == '-') {
            if (s.length() == 1) {
                return false;
            }
            startIndex = 1;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (Character.digit(s.charAt(i), 10) < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isSplitChar(char character, Pattern pattern) {
        if (pattern == Pattern.INTEGER) {
            return isSplitCharForInt(character);
        }  else if (pattern == Pattern.WORD) {
            return isSplitCharForWord(character);
        } else {
            throw new IllegalArgumentException("not supported argument: " + pattern);
        }
    }

    private boolean isSplitCharForWord(char character) {
        return !Character.isLetter(character) && character != '\'' &&
                Character.getType(character) != Character.DASH_PUNCTUATION;
    }

    private boolean isSplitCharForInt(char character) {
        return !Character.isDigit(character) &&
                Character.getType(character) != Character.DASH_PUNCTUATION;
    }

    private enum Pattern {
        INTEGER,
        WORD
    }

    private record Cache(Pattern pattern, String value, int cachePointer) {}
}
