package edu.technopolis;

public class FibonacciSolver implements FibonacciAlgorithm {

    @Override
    public String evaluate(int index) {
        if (index < 3) {
            return "1";
        }
        CustomBigInteger one = new CustomBigInteger(1L);
        CustomBigInteger two = new CustomBigInteger(1L);
        CustomBigInteger result = one;
        for (int i = 2; i < index; i++) {
            result = one.add(two);
            one = two;
            two = result;
        }

        return result.toString();
    }

}
