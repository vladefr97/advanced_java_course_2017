package edu.technopolis;

import java.util.Arrays;

import edu.technopolis.FibonacciAlgorithm;

/**
 * Created by Dmitriy on 10/5/2017.
 */
public class DummyFibonacciAlgorithm implements FibonacciAlgorithm {
    public String evaluate(int n){
        long [] num1 = new long[1];
        long [] num2 = new long[1];

        Arrays.fill(num1, 0);
        Arrays.fill(num2, 0);
        num1[0] = 1;
        num2[0] = 1;

//        char c1 = 1, c2 = 1, ccpy = 0;

        FibNumber fib1 = new FibNumber(num1);
        FibNumber fib2 = new FibNumber(num2);
        for (int i = 0; i < n - 2; i++) {
            FibNumber fibCopy = new FibNumber();
            fibCopy.numbersCnt = fib2.numbersCnt;
            fibCopy.setNumber(fib2.getNumber());
            fib2 = FibNumber.add(fib1, fib2);
            fib1.setNumber(fibCopy.getNumber());
            fib1.numbersCnt = fibCopy.getNumbersCnt();

        }
        return fib2.toString();
    }
}
