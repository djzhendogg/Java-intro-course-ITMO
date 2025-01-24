import java.io.*;
import java.util.*;

public class Wspp {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: java WordStatInput <input_file_name> <output_file_name>");
        }
        try {
            Scanner reader = new Scanner(new FileInputStream(args[0]));
            Map<String, List<Integer>> wordMap = new LinkedHashMap<>();
            try {
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(args[1]),
                                "UTF8"
                        )
                );
                try {
                    int state = 1;
                    while (reader.hasNextWord()) {
                        String word = reader.nextWord().toLowerCase();
                        List<Integer> statList = wordMap.getOrDefault(word, new ArrayList<>());
                        statList.add(state);
                        wordMap.put(word, statList);
                        state++;
                    }
                    for (Map.Entry<String, List<Integer>> entry : wordMap.entrySet()) {
                        writer.write(entry.getKey() + " " + entry.getValue().size());
                        for (int number: entry.getValue()) {
                            writer.write(" " + number);
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
}
