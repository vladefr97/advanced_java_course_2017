package edu.technopolis;

import edu.technopolis.FibonacciAlgorithm;
import igorlo.*;

public class MyFibonacciAlgorythm implements FibonacciAlgorithm {
    @Override
    public String evaluate(int index) {
        if (index < 3) {
            return "1";
        }
        VeryLong one = new VeryLong(4000, 1L);
        VeryLong two = new VeryLong(4000, 1L);
        VeryLong result = new VeryLong(4000, 1L);
        for (int i = 2; i < index; i++) {
            result = VeryLong.getSum(one, two);
            one.set(two);
            two.set(result);
        }
        return result.toString();
    }
}
