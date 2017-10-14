import org.junit.jupiter.api.Test;
import algo.BigFibonacci;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFibonacci {
    @Test
    void TestSimple() {
        assertEquals("0", BigFibonacci.findIterative(0).toString());
        assertEquals("1", BigFibonacci.findIterative(1).toString());
        assertEquals("1", BigFibonacci.findIterative(2).toString());
        assertEquals("2", BigFibonacci.findIterative(3).toString());
        assertEquals("832040", BigFibonacci.findIterative(30).toString());
    }
    @Test
    void TestAnother(){
        assertEquals("832040", BigFibonacci.findIterative(30).toString());
    }
}
