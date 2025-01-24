package expression.generic.expressions;

public class Variable<T extends Number> implements TripleExpression<T> {
    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        if (value.endsWith("x")) {
            return x;
        } else if (value.endsWith("y")) {
            return y;
        } else if (value.endsWith("z")) {
            return z;
        } else {
            throw new IllegalArgumentException("Invalid variable value: 'x', 'y', 'z' expected.'");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Variable<?> variable = (Variable<?>) obj;
        // TODO: наверное нагляднее this.value.equals(otherVariable.value), но щас не принципиально
        return value.equals(variable.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
