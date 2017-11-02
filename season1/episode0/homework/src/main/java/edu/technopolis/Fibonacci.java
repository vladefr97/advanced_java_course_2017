package edu.technopolis;

import java.math.BigInteger;
import java.util.Arrays;

public class Fibonacci implements FibonacciAlgorithm {
    private final int sizeOfNumber = 4;
    private long[] numberOne = new long[sizeOfNumber];
    private long[] numberTwo = new long[sizeOfNumber];

    private long[] sum (long[] one, long[] two) {
        int length = one.length;
        long[] result = new long[length];

        for (int i = 0; i < length - 1; i++) {
            result[i] += one[i] + two[i];
            if (result[i] < 0) {
                result[i + 1] = 1;
                result[i] -= Long.MIN_VALUE;
            }
        }

        result[length - 1] += one[length - 1] + two[length - 1];

        if (result[length - 1] < 0) {
            numberOne = Arrays.copyOf(numberOne, length + 1);
            numberTwo = Arrays.copyOf(numberTwo, length + 1);
            result    = Arrays.copyOf(result, length + 1);

            result[length] = 1;
            result[length - 1] -= Long.MIN_VALUE;
        }

        return result;
    }

    @Override
    public String evaluate(int index) {
        if (index < 0) return "0";

        switch (index) {
        case 0:
        case 1:
        case 2:
            return String.valueOf(index);
        }

        long[] numberRes;

        numberOne[0] = 1;
        numberTwo[0] = 1;

        for (int i = 3; i <= index; i++) {
            numberRes = sum(numberOne, numberTwo);
            numberOne = numberTwo;
            numberTwo = numberRes;
        }

        BigInteger bigMinLong   = new BigInteger(Long.toString(Long.MIN_VALUE)).multiply(BigInteger.valueOf(-1));
        BigInteger bigNumberTwo = new BigInteger(Long.toString(numberTwo[numberTwo.length - 1]));

        for (int i = numberTwo.length - 2; i > -1; i--) {
            bigNumberTwo = bigNumberTwo.multiply(bigMinLong);
            bigNumberTwo = bigNumberTwo.add(BigInteger.valueOf(numberTwo[i]));
        }

        return bigNumberTwo.toString();
    }
}
