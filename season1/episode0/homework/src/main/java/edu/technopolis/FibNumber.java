package edu.technopolis;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by Dmitriy on 9/25/2017.
 */
public class FibNumber {
    long [] number;
    public int numbersCnt = 1;

    public int getNumbersCnt() {
        return numbersCnt;
    }

    FibNumber(){
        this.number = new long[numbersCnt];
        Arrays.fill(this.number, 0);
    }

    FibNumber(long [] number){
        this.number = new long[numbersCnt];
        Arrays.fill(this.number, 0);
        this.number = number;
    }


    public void setNumber(long[] number) {
        this.number = number;
    }

    @Override
    public String toString() {
        BigInteger big = new BigInteger("0");
//        BigInteger addition = new BigInteger("1");
//        addition.shiftLeft(64);
        for (int i = numbersCnt - 1; i >= 0; i--) {
            big = big.shiftLeft(64);
            BigInteger big1 = new BigInteger(Long.toUnsignedString(number[i]));
            big = big.add(big1);
        }
        return big.toString();
    }

    public long[] getNumber() {
        return number;
    }

    static FibNumber add(FibNumber num1, FibNumber num2){
        int additionalDecimal = 0;
        FibNumber res = new FibNumber();
        int resNumbersCnt = Math.max(num1.getNumbersCnt(), num2.getNumbersCnt());
        res.number = Arrays.copyOf(res.number, resNumbersCnt);
        res.numbersCnt = resNumbersCnt;
        for (int i = 0; i < resNumbersCnt; i++) {
            boolean isOneShorter = false;
            if(num1.getNumbersCnt() > i) {
                res.number[i] += num1.number[i];
            }else{
                isOneShorter = true;
            }
            if(num2.getNumbersCnt() > i) {
                res.number[i] += num2.number[i];
            }else{
                isOneShorter = true;
            }
            res.number[i] += additionalDecimal;
            additionalDecimal = 0;
            if(     additionalDecimal == 1 && res.number[i] == 0)
                additionalDecimal = 1;
            if(     additionalDecimal == 0 && isOneShorter == false &&
                    (num1.number[i] < 0 && num2.number[i] < 0 ||
                    0 < res.number[i] && num1.number[i] < 0 ||
                    0 < res.number[i] && num2.number[i] < 0))
                additionalDecimal = 1;
//            else
//                additionalDecimal = 0;

        }
        if (additionalDecimal == 1){
            res.number = Arrays.copyOf(res.number, ++res.numbersCnt);
            res.number[res.numbersCnt - 1] = 1;
        }
        return res;
    }
}
