package edu.technopolis;

public class MyFibonacciAlgorithm implements FibonacciAlgorithm {
    @Override
    public String evaluate(int index) throws Exception {
        BigLongCase a = new BigLongCase(1);
        BigLongCase b = new BigLongCase(1);
        for (int i = 1; i < index; i++) {
            if (i%2 == 1){
                b.sumBig(a);
            }else{
                a.sumBig(b);
            }
        }
        if (index%2 == 1) return b.toString();
        return a.toString();
    }
}
