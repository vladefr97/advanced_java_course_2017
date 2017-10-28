package edu.technopolis;

/**
 * Created by alexandr on 10.10.17.
 */
public class Fibonacci implements FibonacciAlgorithm {

    public String evaluate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        BigInteger a1 = new BigInteger(1L);
        BigInteger a2 = new BigInteger(1L);
        for (int i = 0; i < (n - 2); i++) {
            if ((i % 2) == 0) {
                a1.add(a2);
            } else {
                a2.add(a1);
            }
        }
        if ((n % 2) == 0) {
            return a2.toString();
        } else {
            return a1.toString();
        }
    }

}
