package algo;

import bigInt.BigInt;
import main.java.edu.technopolis.FibonacciAlgorithm;

public class BigFibonacci implements FibonacciAlgorithm{
    public static BigInt findIterative(int n){
        if (n == 0) return new BigInt();
        if (n < 3) return new BigInt("1");
        BigInt next = new BigInt("1");
        BigInt prev = new BigInt("1");
        for (int i = 2; i < n; i++) {
            BigInt temp = prev;
            prev = next;
            next = BigInt.sum(temp,next);
        }
        return next;
    }
    /*
    TODO: add calculation Through Matrix, requires Multiplication
    public static bigInt findThroughMatrix(int n){
        bigInt[] matrix = new bigInt[4];
        matrix[0] = new bigInt("1");
        matrix[1] = new bigInt("1");
        matrix[2] = new bigInt("1");
        matrix[3] = new bigInt();
        return new bigInt();
    }

    private static void unitMatrix(){
        bigInt[] matrix = new bigInt[4];
        matrix[0] = new bigInt("1");
        matrix[1] = new bigInt();
        matrix[2] = new bigInt("1");
        matrix[3] = new bigInt();
    }
    private static void powMatrix(){

    }*/

    @Override
    public String evaluate(int index) {
        return findIterative(index).toString();
    }
}
