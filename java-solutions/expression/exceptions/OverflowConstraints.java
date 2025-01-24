package expression.exceptions;

public class OverflowConstraints {
    public static boolean checkDivideOverflow(int left, int right) {
        return left == Integer.MIN_VALUE && right == -1;
    }

    public static boolean checkMultiplyOverflow(int left, int right) {
        int maxVal = Integer.signum(left) == Integer.signum(right) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        return left != 0 && (
                (right > 0 && maxVal / left > 0 && maxVal / left < right) || (right < 0 && maxVal / left > right)
        );
    }

    public static boolean checkAddOverflow(int left, int right) {
        return left > 0 ? Integer.MAX_VALUE - left < right : Integer.MIN_VALUE - left > right;
    }

    public static boolean checkSubtractOverflow(int left, int right) {
        int c = left - right;
        if (left > right) {
            return c < 0;
        } else {
            return c > 0;
        }
    }
}
