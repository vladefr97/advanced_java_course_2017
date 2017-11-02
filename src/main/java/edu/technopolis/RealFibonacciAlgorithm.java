package edu.technopolis;


public class RealFibonacciAlgorithm implements FibonacciAlgorithm {


    public static BigNumbers getFibonacciNumber(int index) {

        BigNumbers number1 = new BigNumbers(1);
        BigNumbers number2 = new BigNumbers(1);
        BigNumbers temp = new BigNumbers();

        for (int i = 0; i < index; i++) {
            temp.inputNumber(number2);
            number2.add(number1);
            number1.inputNumber(temp);
        }
        return number2;
    }

    @Override
    public String evaluate(int index) {
        return getFibonacciNumber(index-2).toString();
    }
}


    /*
        @Override
        public String evaluate(int index) {
            if (index < 3) {
                return "1";
            }
//            BigInteger one = BigInteger.ONE;
//            BigInteger two = BigInteger.ONE;
//            BigInteger result = one;
//            for (int i = 2; i < index; i++) {
//                result = one.add(two);
//                one = two;
//                two = result;
//            }
//            return result.toString();
            return "0";
        }
*/











