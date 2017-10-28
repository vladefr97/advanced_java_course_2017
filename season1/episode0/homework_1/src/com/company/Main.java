package com.company;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        for (int i = 1; i < 366; i++){
            System.out.print(i + " : ");
            FibCounter.newInstance(i);
        }
    }
}