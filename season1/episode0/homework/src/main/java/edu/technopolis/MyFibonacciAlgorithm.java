package edu.technopolis;

public class MyFibonacciAlgorithm implements FibonacciAlgorithm {
    @Override
    public String evaluate(int index) {
        LongNumber prev = new LongNumber(1, 0);
        LongNumber cur = new LongNumber(1, 1);
        for (int i = 1; i < index; i++) {
            LongNumber temp = cur;
            cur = longArithmetic.sum(prev, cur);
            prev = temp;
        }
        return cur.toString();
    }
}
