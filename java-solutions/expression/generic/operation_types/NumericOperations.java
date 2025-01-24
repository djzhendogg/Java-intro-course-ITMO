package expression.generic.operation_types;

public interface NumericOperations<T extends Number> {
    T add(T left, T right);
    T substarct(T left, T right);
    T multiply(T left, T right);
    T divide(T left, T right);
    T negate(T left);
    T cast(String value);
    default T cast(Number value) {
        return cast(String.valueOf(value));
    }
}
