import bigInt.BigInt;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBigInt {
    @Test
    void testConstructorString(){
        assertEquals("1",new BigInt("1").toString());
        assertEquals("90092132312009",new BigInt("90092132312009").toString());
        assertEquals("900009",new BigInt("900009").toString());
    }
    @Test
    void testConstructorZero(){
        assertEquals("0",new BigInt().toString());
    }
    @Test
    void testCopyConstructor(){
        BigInt a = new BigInt("1");
        BigInt b = new BigInt(a);
        assertEquals("1",b.toString());
        a.add(a);
        assertEquals("1",b.toString());
        assertEquals("2",a.toString());
    }
    @Nested
    class SumTest {
        @Test
        void testAddZeroZero() {
            BigInt a = new BigInt();
            BigInt b = new BigInt();
            a.add(b);
            assertEquals("0", a.toString());
        }
        @Test
        void testAdd1to2() {
            BigInt a = new BigInt("1");
            BigInt b = new BigInt("2");
            a.add(b);
            assertEquals("3", a.toString());
        }
        @Test
        void testAddCarry() {
            BigInt a = new BigInt("999999999");
            BigInt b = new BigInt("1");
            a.add(b);
            assertEquals("1000000000", a.toString());
        }
        @Test
        void testAddCarryMore() {
            BigInt a = new BigInt("999999999999999999");
            BigInt b = new BigInt("1");
            a.add(b);
            assertEquals("1000000000000000000", a.toString());
        }
        @Test
        void testSumCreatesTemp() {
            BigInt a = new BigInt("100");
            BigInt b = new BigInt("21");
            BigInt c = BigInt.sum(a,b);
            assertEquals("121", c.toString());
            assertEquals("100", a.toString());
            assertEquals("21", b.toString());
        }
    }
}
