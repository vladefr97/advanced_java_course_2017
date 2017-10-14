package edu.technopolis;

public class MyFibonacciAlgorithm implements FibonacciAlgorithm {
    @Override
    public String evaluate(int number) {
        if (number < 3) {
            return "1";
        }

        ExtendedLong first = new ExtendedLong(1);
        ExtendedLong second = new ExtendedLong(2);

        // switching between two ELs in order not to use extra EL for result
        byte whereToCalcSum = 0;

        for (int i = 3; i < number; i++) {
            if (whereToCalcSum % 2 == 0)
                first.sum(second);
            else
                second.sum(first);
            whereToCalcSum++;
        }

        return whereToCalcSum % 2 == 0
                ? second.toString()
                : first.toString();
    }
}
