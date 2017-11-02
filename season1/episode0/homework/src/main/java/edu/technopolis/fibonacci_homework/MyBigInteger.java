package edu.technopolis.fibonacci_homework;

import java.math.BigInteger;
import java.util.Arrays;

public class MyBigInteger {
    private static final long BASE = Long.MAX_VALUE;
    private static final int DEFAULT_SIZE = 1;
    private long[] digits;

    public MyBigInteger() {
        this.digits = new long[DEFAULT_SIZE];
    }

    public MyBigInteger(final int sizeOfDigits) {
        this.digits = new long[sizeOfDigits];
    }

    public MyBigInteger(final int sizeOfDigits, final long firstValue) {
        this.digits = new long[sizeOfDigits];
        digits[0] = firstValue;
    }

    public MyBigInteger add(final MyBigInteger that) {
        final int thisSize = this.digits.length;
        final int thatSize = that.digits.length;
        final int maxSize = Math.max(thisSize, thatSize);

        MyBigInteger result = new MyBigInteger(maxSize);
        long residual = 0;

        for (int i = 0; i < maxSize || residual == 1; i++) {
            result.digits[i] =    (i < thisSize ? this.digits[i] : 0)
                                + (i < thatSize ? that.digits[i] : 0)
                                + residual;

            if (result.digits[i] < 0) {
                if (i == maxSize - 1) {
                    result.digits = Arrays.copyOf(result.digits, maxSize + 1);
                }
                result.digits[i] -= BASE;
                residual = 1;
            } else {
                residual = 0;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        BigInteger base = BigInteger.valueOf(BASE);
        BigInteger pow = BigInteger.valueOf(BASE);
        BigInteger result = BigInteger.valueOf(digits[0]);

        for (int i = 1; i < digits.length; i++) {
            BigInteger digit = BigInteger.valueOf(digits[i]).multiply(pow);
            pow = pow.multiply(base);
            result = result.add(digit);
        }
        return result.toString();
    }
}