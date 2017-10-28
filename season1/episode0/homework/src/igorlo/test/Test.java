package igorlo.test;

import java.math.BigInteger;

import igorlo.VeryLong;

public class Test {

    public static void main(String[] args) {

        boolean shouldPrintProcess = true;
        testAll(shouldPrintProcess);


    }

    private static void testAll(boolean shouldPrint) {

        boolean foundTrouble = false;

        //Данный тест считает N-ную степень двойки
        if (!powersOfTwoTest(shouldPrint, 2000))
            foundTrouble = true;

        //Данный тест достаточно просто тестирует основные методы класса VeryLong
        if (!commonTest(shouldPrint))
            foundTrouble = true;

        if (foundTrouble)
            System.out.println("Something gone wrong!");
        else
            System.out.println("All test passed! Congrats!");
    }

    private static boolean powersOfTwoTest(boolean sholdPrintProcess, int power) {
        //Данный тест считает N-ную степень двойки

        long startValue = 1;

        BigInteger big = new BigInteger(Long.toString(startValue));
        VeryLong veryLong = new VeryLong(100, startValue);

        BigInteger stepBig;
        VeryLong stepLong = new VeryLong(100);

        boolean foundTrouble = false;
        int count = 0;

        while (!foundTrouble && count < power) {
            count++;
            stepBig = big;
            big = big.add(stepBig);
            stepLong.set(veryLong);
            veryLong.add(stepLong);
            String bigStr = big.toString();
            String veryStr = veryLong.toString();
            if (bigStr.equals(veryStr)) {
                if (sholdPrintProcess)
                    System.out.println("OK with 2^" + count + ": " + bigStr);
            } else {
                if (sholdPrintProcess) {
                    System.out.println("-----------|-----------");
                    System.out.println("Trouble in:|" + bigStr);
                    System.out.println("You said:  |" + veryStr);
                    System.out.println("Power of 2:|" + count);
                    System.out.println("-----------|-----------");
                    System.out.println("Long MAX:  |" + Long.toString(Long.MAX_VALUE));
                    System.out.println("Long MIN:  |" + Long.toString(Long.MIN_VALUE));
                    System.out.println("-----------|-----------");
                }
                foundTrouble = true;
            }
        }

        if (foundTrouble) {
            System.out.println("Error in powersOfTwoTest");
            return false;
        }

        System.out.println("Success.");
        return true;

    }

    private static boolean commonTest(boolean shouldPrintProcess) {

        boolean foundTrouble = false;

        VeryLong veryLong = new VeryLong(100L);

        if (veryLong.toString().equals("100"))
            System.out.print(shouldPrintProcess ? "Initialization is OK\n" : "");
        else {
            foundTrouble = true;
            System.out.print(shouldPrintProcess ? "Initialization failed\n" : "");
        }

        veryLong.add(new VeryLong(20L));
        if (veryLong.toString().equals("120"))
            System.out.print(shouldPrintProcess ? "SumTest1 is OK\n" : "");
        else {
            foundTrouble = true;
            System.out.print(shouldPrintProcess ? "SumTest1 failed\n" : "");
        }

        veryLong.add(new VeryLong((long) Integer.MAX_VALUE));
        if (veryLong.toString().equals(Long.toString((100L + 20L + ((long) Integer.MAX_VALUE)))))
            System.out.print(shouldPrintProcess ? "SumTest2 is OK\n" : "");
        else {
            foundTrouble = true;
            System.out.print(shouldPrintProcess ? "SumTest2 failed\n" : "");
        }

        veryLong.set(new VeryLong(Long.MAX_VALUE));
        if (veryLong.toString().equals(Long.toString(Long.MAX_VALUE)))
            System.out.print(shouldPrintProcess ? "Setting a Value is OK\n" : "");
        else {
            foundTrouble = true;
            System.out.print(shouldPrintProcess ? "Setting a Value failed\n" : "");
        }

        if (foundTrouble) {
            System.out.println("Error in commonTest");
            return false;
        }
        System.out.println("Success.");
        return true;
    }

}
