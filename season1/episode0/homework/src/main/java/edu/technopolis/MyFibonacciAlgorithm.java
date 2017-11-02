package edu.technopolis;
import java.math.BigInteger;

public class MyFibonacciAlgorithm implements FibonacciAlgorithm {

    public String evaluate (int index)
    {

        MyBigInteger x = new MyBigInteger((long)1);
        MyBigInteger y = new MyBigInteger((long)0);
        MyBigInteger temp;
        for (int i = 1; i < index; i++)
        {
            temp = x;
            x = x.plus(y);
            y = temp;
        }
        return x.toString();

    }



}
