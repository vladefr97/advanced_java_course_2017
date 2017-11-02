package edu.technopolis.fibonacci_homework;

import edu.technopolis.FibonacciAlgorithm;

public class MyFibonacciAlgorithm implements FibonacciAlgorithm {
    @Override
    public String evaluate(final int index) {
        if (index < 3) {
            return "1";
        }
        MyBigInteger first = new MyBigInteger(1, 1);
        MyBigInteger second = new MyBigInteger(1, 1);
        MyBigInteger third = new MyBigInteger();

        for (int i = 2; i < index; i++) {
            third = first.add(second);
            first = second;
            second = third;
        }

        return third.toString();
    }
}