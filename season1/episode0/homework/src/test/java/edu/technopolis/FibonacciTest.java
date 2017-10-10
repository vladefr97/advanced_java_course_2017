package edu.technopolis;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {

    @Test(expected = InvalidParameterException.class)
    public void evaluateNegative() throws Exception {
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.evaluate(-2);
    }

    @Test
    public void evaluateZero() throws Exception {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals("Wrong evaluation for zero", "0", fibonacci.evaluate(0));
    }

    @Test
    public void evaluateOne() throws Exception {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals("Wrong evaluation for one", "1", fibonacci.evaluate(1));
    }

    @Test
    public void evaluateTwo() throws Exception {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals("Wrong evaluation for two", "1", fibonacci.evaluate(2));
    }

    @Test
    public void evaluateThree() throws Exception {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals("Wrong evaluation for three", "2", fibonacci.evaluate(3));
    }

    @Test
    public void evaluate1000() throws Exception {
        Fibonacci fibonacci = new Fibonacci();
        String f1000 = new Scanner(new File("src/test/resources/f1000.txt")).nextLine();
        Assert.assertEquals("Wrong evaluation for 1000", f1000, fibonacci.evaluate(1000));
    }

    @Test
    public void evaluate12000() throws Exception {
        Fibonacci fibonacci = new Fibonacci();
        String f12000 = new Scanner(new File("src/test/resources/f12000.txt")).nextLine();
        Assert.assertEquals("Wrong evaluation for 1000", f12000, fibonacci.evaluate(12000));
    }
}