package com.company;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class FibCounter {
    private long fibNum; //num of count of fib
    private List<Long> number1 = new ArrayList<>(Arrays.asList(
            new Long[] {0l, 0l, 0l, 0l}
    )); //the number 10101
    private List<Long> number2 = new ArrayList<>(Arrays.asList(
            new Long[] {1l, 0l, 0l, 0l}
    ));

    private FibCounter(long fibNum) {
        this.fibNum = fibNum;
        for (int j = 2; j < fibNum; j++) {
            List<Long> changeNum = countNum();
            for (int i = 0; i < number2.size(); i++) {
                number1.set(i, number2.get(i));
                number2.set(i, changeNum.get(i));
            }
        }

        if (fibNum == 1){
            System.out.println(0);
        } else {
            System.out.println(this.getNum());
        }
    }

    public static FibCounter newInstance(long fibNum){
        return new FibCounter(fibNum);
    }

    private boolean isLongFull(Long number1, Long number2, Long ad) {
        if ((number1 + number2 + ad) < 0){
            return true;
        } else {
            return false;
        }
    }

    private List<Long> countNum() {
        List<Long> countedNumber = new ArrayList<>();
        long ad = 0;
        for (int i = 0; i < number2.size(); i++){
            if (isLongFull(number1.get(i), number2.get(i), ad) && i == number2.size() - 1){
                throw new LongFullException();
                // for more longs
                /*long a = 0;
                number2.add(a);
                number1.add(a);*/
            }

            if (!isLongFull(number1.get(i), number2.get(i), ad)) {
                countedNumber.add(number1.get(i) + ad + number2.get(i));
                ad = 0;
            } else {
                long a = number1.get(i);
                long b = number2.get(i);
                long c = ad;

                b -= (Long.MAX_VALUE - a);
                a = Long.MAX_VALUE;

                if ((c + b) < 0){
                    c -= (Long.MAX_VALUE - b);
                    b = Long.MAX_VALUE;
                }

                ad = a / Long.MAX_VALUE + b/Long.MAX_VALUE + c / Long.MAX_VALUE;
                countedNumber.add(a % Long.MAX_VALUE + b % Long.MAX_VALUE + c % Long.MAX_VALUE);
            }
        }
        return countedNumber;
    }

    private BigInteger getNum() {
        BigInteger big0, big1, big2, big3, one;
        one = BigInteger.valueOf(Long.MAX_VALUE);
        big1 = BigInteger.valueOf(number2.get(1)).multiply(one);
        big2 = BigInteger.valueOf(number2.get(2)).multiply(one).multiply(one);
        big3 = BigInteger.valueOf(number2.get(3)).multiply(one).multiply(one).multiply(one);
        big0 = BigInteger.valueOf(number2.get(0)).add(big1).add(big2).add(big3);
        return big0;
    }
}