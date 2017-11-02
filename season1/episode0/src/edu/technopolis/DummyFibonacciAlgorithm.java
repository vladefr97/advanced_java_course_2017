package edu.technopolis;


public class DummyFibonacciAlgorithm implements FibonacciAlgorithm {

    @Override
    public String evaluate(int index) {
        if (index < 3) {
            return "1";
        }
        MyBigInteger one = new MyBigInteger((byte)1);
        MyBigInteger two = new MyBigInteger((byte)1);
        MyBigInteger result = one;
        for (int i = 2; i < index; i++) {
            result = one.add(two);
            one = two;
            two = result;
        }

        return result.toString();
    }
}