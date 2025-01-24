package md2html;

import markup.AbstractTextDecorator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: java WordStatInput <input_file_name> <output_file_name>");
        }
        try {
            MarkDownBlockReader reader = new MarkDownBlockReader(new FileInputStream(args[0]));
            try {
                BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8
                    )
                );
                try {
                    while (reader.hasNext()) {
                        StringBuilder htmlString = new StringBuilder();
                        AbstractTextDecorator paragraph = reader.nextBlock();
                        paragraph.toHtml(htmlString);
                        writer.write(htmlString.toString());
                        writer.newLine();
                    }
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
