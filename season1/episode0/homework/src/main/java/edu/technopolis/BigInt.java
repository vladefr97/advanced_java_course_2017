package edu.technopolis;

import java.math.BigInteger;
import java.util.Arrays;

class BigInt {
    private int size = DEFAULT_SIZE;
    private final static int DEFAULT_SIZE = 1;
    private long[] values = new long[DEFAULT_SIZE];

    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() && Arrays.equals(values, ((BigInt) o).values);
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder number = new StringBuilder();
        for (long value : values) {
            String num = Long.toBinaryString(value);

            number.insert(0, num);
            for (int i = 0; i < Long.SIZE - num.length(); i++) {
                number.insert(0, '0');
            }
        }

        return new BigInteger(number.toString(), 2).toString();
    }

    public BigInt plus(BigInt other) {
        int newSize;
        if (this.size > other.size) {
            newSize = this.size;
            other.values = Arrays.copyOf(other.values, newSize);
            other.size = newSize;
        } else if (this.size < other.size) {
            newSize = other.size;
            this.values = Arrays.copyOf(this.values, newSize);
            this.size = newSize;
        } else {
            newSize = this.size;
        }

        long[] res = new long[newSize]; //If will be overflow
        for (int i = 0; i < newSize; i++) {
            if ((this.values[i] < 0 && other.values[i] < 0) ||
                    ((this.values[i] < 0 || other.values[i] < 0) && (res[i] + this.values[i] + other.values[i]) >= 0)) {
                if (i + 1 == newSize) {
                    res = Arrays.copyOf(res, newSize + 1);
                }
                res[i + 1]++;
            }

            res[i] += this.values[i] + other.values[i];
        }

        return new BigInt(res);
    }

    private BigInt(long[] values) {
        this.values = values;
        size = values.length;
    }

    public BigInt(long value) {
        this.values[0] = value;
    }
}