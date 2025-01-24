import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccurateMovement {
    public static void main(String[] args) {
        List<Integer> data = readIntList();
        int a = data.get(0);
        int b = data.get(1);
        int n = data.get(2);

        int k = 2 * (int) Math.ceil((double) (n - b) / (b - a)) + 1;
        System.out.println(k);
    }

    public static List<Integer> readIntList() {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(
                    System.in,
                    "UTF8"));
            try {
                char[] buffer = new char[1024];
                int read = reader.read(buffer);
                List<Integer> ints = new ArrayList<>();
                StringBuilder integer = new StringBuilder();
                while (read >= 0) {
                    for (int i = 0; i < read; i++) {
                        if (Character.isWhitespace(buffer[i])) {
                            if (!integer.isEmpty()) {
                                ints.add(Integer.parseInt(integer.toString()));
                                integer.setLength(0);
                            }
                            continue;
                        }
                        integer.append(buffer[i]);
                    }

                    read = reader.read(buffer);
                }
                return ints;

            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return List.of();
    }
}



