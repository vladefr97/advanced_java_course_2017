package edu.technopolis;


import java.math.BigInteger;
import java.util.ArrayList;

//Реализован алгоритм сложения больших чисел на основе Long.Max-овой системы счисления и
//простого школьного сложения в столбик

class MyBigInteger {
    ArrayList<Long> digits;

    public MyBigInteger(ArrayList<Long> digits) {
        this.digits = digits;
    }

    public Long getDigits(int index) {
        return digits.get(index);
    }

    public MyBigInteger(Long digit) {
        digits = new ArrayList<>();
        this.digits.add(digit);
    }

    public MyBigInteger plus(MyBigInteger arg) {
        while (arg.digits.size() < this.digits.size()) {
            arg.digits.add((long) 0);
        }
        while (arg.digits.size() > this.digits.size()) {
            this.digits.add((long) 0);
        }
        int i = 0;
        ArrayList<Long> result = new ArrayList<>();
        ArrayList<Long> AtomicResult;
        int main_flag = 0;
        long flag1;
        long flag2;
        while (i != this.digits.size()) {
            AtomicResult = sumLongDigits(this.digits.get(i), arg.digits.get(i));
            flag1 = AtomicResult.get(1);
            AtomicResult = sumLongDigits(AtomicResult.get(0), (long) main_flag);
            flag2 = AtomicResult.get(1);
            result.add(AtomicResult.get(0));
            if (flag1 == 1 || flag2 == 1) {
                main_flag = 1;
            } else {
                main_flag = 0;
            }
            i++;
        }
        ;
        if (main_flag != 0) {
            result.add((long) main_flag);
        }

        return new MyBigInteger(result);

    }

    ArrayList<Long> sumLongDigits(Long arg1, Long arg2) {
        Long result;
        Byte flag;
        if (arg1 + arg2 < 0) {
            flag = 1;
            result = Long.MAX_VALUE + arg1 + arg2 + 2;
        } else {
            flag = 0;
            result = new Long(arg1 + arg2);
        }

        //return new Pair<Long, Integer>(result, flag);
        //Я бы очень хотел использовать pair, как белый человек, но Gradle против.
        ArrayList<Long> returnValue = new ArrayList<>();
        returnValue.add(result);
        returnValue.add((long)flag);
        return returnValue;

    }

    @Override
    public String toString() {
        BigInteger result = BigInteger.ZERO;
        BigInteger LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
        for (int i = 0; i < this.digits.size(); i++) {
            result = result.add((LONG_MAX_VALUE.pow(i)).multiply(BigInteger.valueOf(this.getDigits(i))));
        }
        return result.toString();
    }
}