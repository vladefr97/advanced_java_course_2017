package edu.technopolis;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.Arrays;

public class Fibonacci implements FibonacciAlgorithm {
    private long[] num1;
    private long[] num2;
    private BigInteger absLongMin = new BigInteger(String.valueOf(Long.MIN_VALUE)
            .replace("-", ""));

    private long[] sum(long[] n1, long[] n2) {
        if (n1.length != n2.length) {
            throw new IllegalArgumentException("Different array length");
        }

        long[] result = new long[n1.length];

        for (int i = 0; i < result.length - 1; i++) {
            result[i] += n1[i] + n2[i];
            if (result[i] < 0) {
                result[i] -= Long.MIN_VALUE;
                result[i + 1] += 1;
            }
        }

        int last = result.length - 1;
        if (result[last] + n1[last] + n2[last] <= 0) {
            num1 = Arrays.copyOf(num1, num1.length + 2);
            num2 = Arrays.copyOf(num2, num2.length + 2);
            result = Arrays.copyOf(result, result.length + 2);
        }

        result[last] += n1[last] + n2[last];
        if (result[last] < 0) {
            result[last] -= Long.MIN_VALUE;
            result[last + 1] += 1;
        }

        return result;
    }

    private String bigToString(long[] number) {
        BigInteger result = BigInteger.valueOf(number[number.length - 1]).multiply(absLongMin);
        for (int i = number.length - 2; i > 0; i--) {
            result = result.add(BigInteger.valueOf(number[i])).multiply(absLongMin);
        }
        result = result.add(BigInteger.valueOf(number[0]));
        return result.toString();
    }

    @Override
    public String evaluate(int index) {
        if (index < 0) {
            throw new InvalidParameterException();
        }

        switch (index) {
        case 0:
            return "0";
        case 1:
            return "1";
        case 2:
            return "1";
        }

        int defaultSize = 4;
        num1 = new long[defaultSize];
        num1[0] = 1;
        num2 = new long[defaultSize];
        num2[0] = 1;

        long[] number = null;

        for (int i = 3; i <= index; i++) {
            number = sum(num1, num2);
            num1 = num2;
            num2 = number;
        }

        return bigToString(number);
    }
}
