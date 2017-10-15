package edu.technopolis;

import java.util.Arrays;
import java.math.BigInteger;

public class MyBigInteger {

    private byte[] digits;
    private static final int startCap = 4;
    private int size;



    public MyBigInteger() {
        digits = new byte [startCap];
        size = startCap;
    }


    public MyBigInteger(byte value) {
        digits = new byte[startCap];
        this.size = startCap;
        digits[0] = value;
    }

    public MyBigInteger(int size) {
        digits = new byte [size];
        this.size = size;
    }

    public MyBigInteger add(MyBigInteger multiplier) {

        MyBigInteger result = new MyBigInteger(multiplier.size);

        for (int i = 0; i < result.size - 1; i++) {


            result.digits[i] += this.digits[i] + multiplier.digits[i];


            if (result.digits[i] < 0) {
                result.digits[i + 1]++;
                result.digits[i] -= Byte.MIN_VALUE;

            }
        }


        result.digits[result.size - 1] += this.digits[result.size - 1] + multiplier.digits[result.size - 1];


        if (result.digits[result.size - 1] < 0) {
            result.digits[result.size - 1] -= Byte.MIN_VALUE;

            int lastSize= result.size - 1;
            result.size+=result.size/4;

            result.digits = Arrays.copyOf(result.digits,result.size);

            multiplier.size+=multiplier.size/4;
            multiplier.digits = Arrays.copyOf(multiplier.digits,multiplier.size);
            result.digits[lastSize+1]++;
        }

        return result;
    }

    @Override
    public String toString() {

        BigInteger BASE = BigInteger.valueOf(Byte.MAX_VALUE).add(BigInteger.ONE);
        BigInteger result = BigInteger.ZERO;
        BigInteger BaseMultiplier = BigInteger.ONE;


        for (int i = 0; i < size; i++) {
            result = result.add(BigInteger.valueOf(digits[i]).multiply(BaseMultiplier));
            BaseMultiplier = BaseMultiplier.multiply(BASE);
        }

        return result.toString();
    }

}