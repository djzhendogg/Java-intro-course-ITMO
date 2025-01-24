package expression;

public class Variable implements VariableExpression {
    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
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
        Variable variable = (Variable) obj;
        return value.equals(variable.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
