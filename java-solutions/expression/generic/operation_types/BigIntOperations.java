package expression.generic.operation_types;

import expression.exceptions.expresion_exceptions.ConstValueException;
import expression.exceptions.expresion_exceptions.ZeroDivisionException;

import java.math.BigInteger;

public class BigIntOperations implements NumericOperations<BigInteger> {
    @Override
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger substarct(BigInteger left, BigInteger right) {
        return left.add(right.negate());
    }

    @Override
    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public BigInteger divide(BigInteger left, BigInteger right) {
        if (right.equals(BigInteger.ZERO)) {
            throw new ZeroDivisionException(this.toString());
        }
        return left.divide(right);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger cast(String value) {
        try {
            return new BigInteger(value);
        } catch (NumberFormatException e) {
            throw new ConstValueException(value, e);
        }
    }
}
