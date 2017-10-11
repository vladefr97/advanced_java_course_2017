package edu.technopolis;

import java.math.BigInteger;

/**
 * Это <b>неправильный</b> с точки зрения задания алгоритм.
 */
public class DummyFibonacciAlgorithm implements FibonacciAlgorithm {
    @Override
    public String evaluate(int index) {
        if (n < 3) {
            return "1";
        }
        BigArithmetic one = new BigArithmetic(1L,4000);
        BigArithmetic two =new BigArithmetic(1L,4000);
        BigArithmetic result = one;
        for (int i = 2; i < n; i++) {
            one.add(two);
            result=one;
            one = two;
            two = result;
        }

        return result.toString();
    }
}
