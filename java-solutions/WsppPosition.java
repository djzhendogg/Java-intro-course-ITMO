import java.io.*;
import java.util.*;

public class WsppPosition {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: java WordStatInput <input_file_name> <output_file_name>");
        }
        try {
            Scanner reader = new Scanner(new FileInputStream(args[0]));
            Map<String, List<LineCounter>> wordMap = new LinkedHashMap<>();
            try {
                BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        "UTF8"
                    )
                );
                try {
                    int lineNum = 0;
                    while (reader.hasNext()) {
                        String line = reader.nextLine();
                        if (line.isEmpty()) {
                            continue;
                        }
                        Scanner stringScanner = new Scanner(line);
                        lineNum++;
                        int numInLine = 1;
                        while (stringScanner.hasNextWord()) {
                            String word = stringScanner.nextWord().toLowerCase();
                            List<LineCounter> statList = wordMap.getOrDefault(word, new ArrayList<>());
                            statList.add(new LineCounter(lineNum, numInLine));
                            wordMap.put(word, statList);
                            numInLine++;
                        }
                    }
                    for (Map.Entry<String, List<LineCounter>> entry : wordMap.entrySet()) {
                        List<LineCounter> statInfo = entry.getValue();
                        writer.write(entry.getKey() + " " + statInfo.size());
                        for (LineCounter linInfo: statInfo) {
                            writer.write(" " + linInfo.getLineNum() + ":" + linInfo.getNumInLine());
                        }
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.err.println("Error I/O operation in read functions: " + Arrays.toString(e.getStackTrace()));
                } finally {
                    writer.close();
                }
            } catch (FileNotFoundException e) {
                System.err.println("Write file not found: " + Arrays.toString(e.getStackTrace()));
            } catch (UnsupportedEncodingException e) {
                System.err.println("Writer encoding is not supported: " + Arrays.toString(e.getStackTrace()));
            } catch (IOException e) {
                System.err.println("Error I/O operation for writer: " + Arrays.toString(e.getStackTrace()));
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Read file found error: " + Arrays.toString(e.getStackTrace()));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Reader encoding is not supported: " + Arrays.toString(e.getStackTrace()));
        } catch (IOException e) {
            System.err.println("Error I/O operation for reader: " + Arrays.toString(e.getStackTrace()));
        }
    }

    private static class LineCounter {
        private final int lineNum;
        private final int numInLine;

        public LineCounter(int lineNum, int numInLine) {
            this.lineNum = lineNum;
            this.numInLine = numInLine;
        }

        public int getLineNum() {
            return lineNum;
        }

        public int getNumInLine() {
            return numInLine;
        }
    }
}
