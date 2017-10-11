package edu.technopolis;

public class GoodFibonacciAlgorithm implements FibonacciAlgorithm {
    @Override
    public String evaluate(int index) {
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
}
