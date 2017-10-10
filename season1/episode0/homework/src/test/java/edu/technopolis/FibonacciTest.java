package edu.technopolis;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {
    @Test
    public void Fibbonaci_100() throws Exception {
        Fibonacci fib = new Fibonacci();
        Assert.assertEquals("354224848179261915075", fib.evaluate(100));
    }

    @Test
    public void Fibbonaci_250() throws Exception {
        Fibonacci fib = new Fibonacci();
        Assert.assertEquals("7896325826131730509282738943634332893686268675876375", fib.evaluate(250));
    }

    @Test
    public void Fibbonaci_500() throws Exception {
        Fibonacci fib = new Fibonacci();
        Assert.assertEquals("139423224561697880139724382870407283950070256587697307264108962948325571622863290691557658876222521294125", fib.evaluate(500));
    }

    @Test
    public void Fibbonaci_750() throws Exception {
        Fibonacci fib = new Fibonacci();
        Assert.assertEquals("2461757021582324272166248155313036893697139996697461509576233211000055607912198979704988704446425834042795269603588522245550271050495783935904220352228801000", fib.evaluate(750));
    }

    @Test
    public void Fibbonaci_1000() throws Exception {
        Fibonacci fib = new Fibonacci();
        Assert.assertEquals("43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875", fib.evaluate(1000));
    }


}