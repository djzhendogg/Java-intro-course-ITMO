import java.util.Scanner;

public class IdealPyramidSc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long xl = Integer.MAX_VALUE;
        long xr = Integer.MIN_VALUE;
        long yl = Integer.MAX_VALUE;
        long yr = Integer.MIN_VALUE;
        for (long i = 0; i < n; i++) {
            long x = sc.nextInt();
            long y = sc.nextInt();
            long h = sc.nextInt();
            if (x - h < xl) {
                xl = x - h;
            }
            if (x + h > xr) {
                xr = x + h;
            }
            if (y - h < yl) {
                yl = y - h;
            }
            if (y + h > yr) {
                yr = y + h;
            }
        }
        long h = (long) Math.ceil((double) Math.max(xr - xl, yr - yl) / 2);
        long x = (xr + xl) / 2;
        long y = (yr + yl) / 2;
        System.out.println(x + " " + y + " "+ h);
    }
}
