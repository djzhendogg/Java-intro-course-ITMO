import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HighLoadDatabase {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in,
                    "UTF8"));
            try {
                int N = 200100;
                int[] a = new int[N];
                int[] f = new int[N];
                int[] sum = new int[N];
                int maxA = 0;
                int n = Integer.parseInt(reader.readLine());
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                for (int num = 1; num <= n; num++) {
                    int ai = Integer.parseInt(tokenizer.nextToken());
                    a[num] = ai;
                    if (ai > maxA) {
                        maxA = ai;
                    }
                    sum[num]=sum[num-1]+a[num];
                }
                int q = Integer.parseInt(reader.readLine());
                line = reader.readLine();
                tokenizer = new StringTokenizer(line, " ");
                for (int qq=0; qq < q; qq++) {
                    int t = Integer.parseInt(tokenizer.nextToken());
                    if (t < maxA) {
                        System.out.println("Impossible");
                        continue;
                    }
                    int last = 1;
                    int ans = 0;
                    while (last <= n) {
                        ans++;
                        int l = last;
                        int r = n;
                        int res = -1;
                        while (l <= r) {
                            int mid = l + r >> 1;
                            if (sum[mid] - sum[last - 1] <= t) {
                                l = mid + 1;
                                res = mid;
                            } else {
                                r = mid - 1;
                            }
                        }
                        last = res + 1;
                    }
                    System.out.println(ans);

                }

            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
