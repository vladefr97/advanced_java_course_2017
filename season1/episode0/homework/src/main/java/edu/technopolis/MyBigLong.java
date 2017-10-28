package edu.technopolis;

import java.math.BigInteger;

public class MyBigLong {

    private static final long TEMP_SUM_MASK = 0x7fffffffffffffffL;
    private static final long TEMP_FIRST_MASK = 0x8000000000000000L;
    /**
     * The capacity of this MyBigLong: 4 for my example.
     */
    private final int capacity = 4;

    /**
     * The signum of this MyBigLong: -1 for negative, 0 for zero, or
     * 1 for positive.
     */
    private final int signum;

    /**
     * Array longs
     */
    private final long[] mag;

    private MyBigLong(long[] mag, int signum) {
        this.signum = signum;
        this.mag = mag;
    }

    public MyBigLong(long value) {
        this.signum = 1;
        this.mag = new long[capacity];
        mag[0] = value;
    }

    public MyBigLong add(MyBigLong var) {
        if (var.signum == 0) {
            return this;
        }
        if (this.signum == 0) {
            return new MyBigLong(var.mag, var.signum);
        }
        if (var.signum == this.signum) {
            return new MyBigLong(add(var.mag, this.mag), signum);
        }
        //другие варианты опущены
        return null;
    }

    private long[] add(long[] x, long[] y) throws IndexOutOfBoundsException {
        // If x is shorter, swap the two arrays
        if (x.length < y.length) {
            long[] tmp = x;
            x = y;
            y = tmp;
        }

        long result[] = new long[x.length];

        for (int i = 0; i < x.length; i++) {
            result[i] += x[i] + y[i];
            if (result[i] < x[i]) {
                result[i] -= Long.MIN_VALUE;
                // Exception here
                result[i + 1]++;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return longsToBigInteger(mag).toString();
    }

    private BigInteger longsToBigInteger(long[] mag) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < mag.length; i++) {
            BigInteger term = BigInteger.ONE;
            for (int j = 0; j < i; j++) {
                term = term.multiply(BigInteger.valueOf(Long.MIN_VALUE).negate());
            }
            term = term.multiply(BigInteger.valueOf(mag[i]));
            result = result.add(term);
        }
        return result;
    }
}
