package edu.technopolis;

public class PresentFibonacciAlgorithm implements FibonacciAlgorithm {
    @Override
    public String evaluate(int index) {

        MyBigLong last = new MyBigLong((long) 0);
        MyBigLong curr = new MyBigLong((long) 1);
        MyBigLong result = curr;
        for (int i = 1; i < index; i++) {
            result = last.add(curr);
            last = curr;
            curr = result;
        }
        return result.toString();
    }
}
