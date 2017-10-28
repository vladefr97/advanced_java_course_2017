package edu.technopolis;

import java.lang.*;
import java.math.BigInteger;

public class FibonacciNumber implements FibonacciAlgorithm{
    private static final int M = 4000; // buffer size

    /*
     * Adds first and second numbers and save result in first
     */
    private void sum(long[] first, long[] second) {
        //overflows: store information about overflows
        boolean[] overflows = new boolean[M];

        overflows[M - 1] = first[M-1] + second[M-1] < 0;

        for (int i = M - 2; i >= 0; --i) {
            overflows[i] = first[i] + second[i] < 0 || (first[i] + second[i] + 1 < 0) && overflows[i + 1];
        }

        //sum numbers

        for (int i = M - 1; i >= 0; --i) {
            if (i<M-1 && overflows[i + 1]) {
                first[i]+= 1;
            }

            if (overflows[i]) {
                first[i]+=Long.MIN_VALUE + second[i];
            } else {
                first[i]+=second[i];
            }
        }
    }

    /*
     * Translate the number into the demical form
     */
    private String result(long[] number) {
        BigInteger res = new BigInteger(String.valueOf(0));

        for (int i = M - 1; i >= 0; --i) {
            BigInteger num = new BigInteger(String.valueOf(number[i]));
            int k = 0;
            for (int j = 0; j < (M - 1) - (i); ++j) {
                num = num.multiply(BigInteger.valueOf(Long.MIN_VALUE));
                k++;
            }
            if (k % 2 == 1) {
                num = num.multiply(BigInteger.valueOf(-1));
            }
            res = res.add(num);
        }

        return res.toString();
    }

    /*
     * Calculating the Fibonacci number by its number
     */
    @Override
    public String evaluate(int number) {
        long[] first = new long[M];
        long[] second = new long[M];

        second[M-1]=1;

        boolean sumInFirst = true; // Variable which control position of next Fibonacci number

        for (int i = 1; i < number; ++i) {
            if (sumInFirst) {
                sum(first, second);
                sumInFirst = false;
            } else {
                sum(second, first);
                sumInFirst = true;
            }
        }

        if (sumInFirst && number != 0) {
            return result(second);
        } else {
            return result(first);
        }
    }
}