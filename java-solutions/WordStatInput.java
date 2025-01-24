import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatInput {

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: java WordStatInput <input_file_name> <output_file_name>");
        }
        try {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(args[0]),
                            "UTF8"
                    )
            )) {
                Map<String, Integer> wordMap = new LinkedHashMap<>();
                try (BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]),
                                "UTF8"
                        )
                )) {
                    char[] fullBuffer = new char[0];
                    int startIndex = 0;
                    int lastSplitCharIndex = 0;

                    while (true) {
                        char[] buffer = new char[startIndex + BUFFER_SIZE];
                        int readedCharNumber = reader.read(buffer, startIndex, BUFFER_SIZE);
                        if (readedCharNumber < 0) {
                            break;
                        }

                        System.arraycopy(fullBuffer, lastSplitCharIndex, buffer, 0, startIndex);
                        fullBuffer = buffer;

                        lastSplitCharIndex = 0;

                        for (int i = startIndex; i < fullBuffer.length; i++) {
                            if (isSplitChar(fullBuffer[i])) {
                                if (lastSplitCharIndex != i) {
                                    String word = new String(
                                            fullBuffer,
                                            lastSplitCharIndex,
                                            i - lastSplitCharIndex
                                    ).toLowerCase();
                                    wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                                }
                                lastSplitCharIndex = i + 1;
                            }
                        }
                        startIndex = fullBuffer.length - lastSplitCharIndex;
                    }
                    if (startIndex > 0) {
                        String word = new String(fullBuffer, 0, startIndex).toLowerCase();
                        wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                    }
                    for (String key : wordMap.keySet()) {
                        writer.write(key + " " + wordMap.get(key));
                        writer.newLine();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found error: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("Encoding is not supported: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error I/O operation: " + e.getMessage());
        }
    }

    public static boolean isSplitChar(char character) {
        return !Character.isLetter(character) && character != '\'' &&
            Character.getType(character) != Character.DASH_PUNCTUATION;
    }
}
