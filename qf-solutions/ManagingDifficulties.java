import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ManagingDifficulties {
    public static void main(String[] args) {
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in, StandardCharsets.UTF_8))) {
                int t = Integer.parseInt(reader.readLine());
                for (int test = 0; test < t; test++) {
                    int n = Integer.parseInt(reader.readLine());
                    String line = reader.readLine();
                    StringTokenizer tokenizer = new StringTokenizer(line, " ");
                    int[] a = new int[n];
                    for (int num = 0; num < n; num++) {
                        a[num] = Integer.parseInt(tokenizer.nextToken());
                    }
                    Map<Integer, Value> c = new HashMap<>();
                    c.put(a[n - 1], new Value(0, 1));
                    for (int j = n - 2; j > 0; j--) {
                        int base = 2 * a[j];
                        for (int i = 0; i < j; i++) {
                            int val = base - a[i];
                            if (c.containsKey(val)) {
                                Value toPut = new Value(c.get(val).value() + c.get(val).retry(), c.get(val).retry());
                                c.put(val, toPut);
                            }
                        }
                        if (!c.containsKey(a[j])) {
                            c.put(a[j], new Value(0, 1));
                        } else {
                            c.put(a[j], new Value(c.get(a[j]).value(), c.get(a[j]).retry() + 1));
                        }
                    }
                    int sum = 0;
                    for (Integer k : c.keySet()) {
                        sum += c.get(k).value();
                    }
                    System.out.println(sum);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public record Value(int value, int retry) {}
}
