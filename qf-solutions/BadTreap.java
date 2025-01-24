import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BadTreap {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in,
                "UTF8"));
            try {
                int z = -710 * 25000;
                int j = 710 * 25000;
                int n = Integer.parseInt(reader.readLine());
                int stop = 0;
                for (int i = z ; i <= j; i += 710) {
                    if (stop >= n) {
                        break;
                    }
                    stop++;
                    System.out.println(i);
                }

            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error reading stream: " + e.getMessage());
        }
    }
}
