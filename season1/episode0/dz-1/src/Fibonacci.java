public class Fibonacci implements FibonacciAlgorithm{
    public String evaluate(int index){
        BigNum a = new BigNum(1);
        BigNum b = new BigNum(1);
        BigNum c = new BigNum();
        if (index == 1 || index == 2){
            return a.getStr().toString();
        }

        for (int i = 3; i <= index; i++)
        {
            c.assign(BigNum.add(a,b));
            a.assign(b);
            b.assign(c);
        }

        return c.getStr().toString();
    }
}
