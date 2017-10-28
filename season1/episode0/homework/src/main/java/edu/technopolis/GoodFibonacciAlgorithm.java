package edu.technopolis;

import java.math.BigInteger;
import java.util.Arrays;

public class GoodFibonacciAlgorithm implements FibonacciAlgorithm {
    @Override
    public String evaluate(int index) {
        if (index < 0) throw new IllegalArgumentException("Less of zero");
        if (index == 0) return "0";
        if (index < 3) {
            return "1";
        }
        BigInt one = new BigInt(1);
        BigInt two = new BigInt(1);
        BigInt result = one;
        for (int i = 2; i < index; i++) {
            result = one.plus(two);
            one = two;
            two = result;
        }
        return result.toString();
    }

    private class BigInt {
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

        BigInt plus(BigInt other) {
            if (other.size > this.size) return other.plus(this);

            long[] res = new long[size];
            for (int i = 0; i < size; i++) {
                long first = this.values[i];
                long second = i >= other.size ? 0 : other.values[i];
                if ((first < 0 && second < 0) ||
                        ((first < 0 || second < 0) && (res[i] + first + second) >= 0)) {
                    if (i + 1 == size) {
                        res = Arrays.copyOf(res, size + 1);
                    }
                    res[i + 1]++;
                }

                res[i] += first + second;
            }

            return new BigInt(res);
        }

        private BigInt(long[] values) {
            this.values = values;
            size = values.length;
        }

       BigInt(long value) {
            this.values[0] = value;
        }
    }
}
