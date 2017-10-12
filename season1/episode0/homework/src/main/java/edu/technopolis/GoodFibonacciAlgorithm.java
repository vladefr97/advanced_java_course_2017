package edu.technopolis;

import java.math.BigInteger;

public class GoodFibonacciAlgorithm implements FibonacciAlgorithm {

    @Override
    public String evaluate(int index) {
        if (index < 3) return "1";
        BigLong bigLong1 = new BigLong(1);
        BigLong bigLong2 = new BigLong(1);

        for (int i = 2; i < index; i++) {
            BigLong tmp = new BigLong(bigLong1);
            tmp.add(bigLong2);
            bigLong1 = bigLong2;
            bigLong2 = tmp;
        }
        return bigLong2.toString();
    }

    private class BigLong {
        private final static int CAPACITY = 4;
        private long[] digits = new long[CAPACITY];

        BigLong(long l) {
            this.digits[CAPACITY - 1] = l;
        }

        public BigLong(BigLong bigLong) {
            digits = bigLong.digits;
        }

        BigLong add(BigLong num) {

            for (int i = 0; i < CAPACITY; i++) {
                if (digits[i] + num.digits[i] < 0) {
                    digits[i] = digits[i] + num.digits[i] - Long.MAX_VALUE - 1;

                    for (int j = i - 1; j >= 0; j--) {
                        if (digits[j] + 1 < 0) {
                            if (j == 0) {
                                throw new RuntimeException("Too large number");
                            } else {
                                digits[j] = 0;
                            }
                        } else {
                            digits[j] += 1;
                            break;
                        }
                    }

                } else {
                    digits[i] += num.digits[i];
                }
            }
            return this;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < CAPACITY; i++) stringBuilder.append("0");
            for (long x : digits) {
                for (int i = 0; i < Long.numberOfLeadingZeros(x) - 1; i++) stringBuilder.append(0);
                if (x != 0) stringBuilder.append(Long.toBinaryString(x));
            }
            return new BigInteger(stringBuilder.toString(), 2).toString();
        }
    }


    public static void main(String[] args) {
        GoodFibonacciAlgorithm g = new GoodFibonacciAlgorithm();
        DummyFibonacciAlgorithm d = new DummyFibonacciAlgorithm();

        for (int i = 0;i<1000000;i++) {
            System.out.println(g.evaluate(i)/* + "  =  " + d.evaluate(i)*/);
        }
    }
}
