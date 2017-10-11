package edu.technopolis;

import java.util.Scanner;

public class Main {
    public static void main(String[] argv) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter index of element: ");
        int index = scn.nextInt(); //получаем индекс
        NormalFibonacciAlgorithm evaluator = new NormalFibonacciAlgorithm();
        String str = new String(evaluator.evaluate(index));
        System.out.println(str);
        return;
    }
}
