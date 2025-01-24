package expression.generic.expressions;

import java.util.Objects;

public class Const<T extends Number> implements TripleExpression<T> {
    private final T value;

    public Const(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Const<?> aConst = (Const<?>) obj;
        return Objects.equals(value, aConst.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
