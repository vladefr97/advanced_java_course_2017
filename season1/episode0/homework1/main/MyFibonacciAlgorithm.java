package advanced_java.homework1.main;

public class MyFibonacciAlgorithm implements FibonacciAlgorithm {

    @Override
    public String evaluate(int index) {
        if (index < 2) {
            return new MyBigInteger(index).toString();
        }
        MyBigInteger first = new MyBigInteger(0);
        MyBigInteger second = new MyBigInteger(1);
        MyBigInteger result = null;
        for (int i = 2; i <= index; i++) {
            result = first.add(second);
            first = second;
            second = result;
        }
        return result.toString();
    }
}
