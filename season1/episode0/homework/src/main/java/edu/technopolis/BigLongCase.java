package edu.technopolis;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BigLongCase {
    ArrayList<Long> arr = new ArrayList<>();

    public BigLongCase(final long num) {
        arr.clear();
        arr.add(num);
    }


    public void sumBig(final BigLongCase b) throws Exception {
        long c = 0;
        while (arr.size() < b.arr.size()){
            arr.add(c);
        }
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).longValue() + c < 0) {
                arr.set(i,b.arr.get(i).longValue());
                c = 1;
                if (i == arr.size() - 1) {
                    arr.add(c);
                    break;
                }
                continue;
            }
            if (arr.get(i).longValue() + c + b.arr.get(i).longValue()< 0) {
                arr.set(i, arr.get(i).longValue() + c + b.arr.get(i).longValue() - Long.MIN_VALUE + 1);
                c = 1;
                if (i == arr.size() - 1) {
                    arr.add(c);
                    break;
                }
            } else {
                arr.set(i, arr.get(i).longValue() + c + b.arr.get(i).longValue());
                c = 0;
            }
        }
    }

    @Override
    public String toString() {
        BigDecimal longMax = new BigDecimal(Long.MAX_VALUE);
        BigDecimal result = new BigDecimal(arr.get(0).longValue());
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).longValue() == 0){
                break;
            }
            BigDecimal tempo = new BigDecimal(Long.MAX_VALUE);
            for (int j = 1; j < i; j++) {
                tempo = tempo.multiply(longMax);
            }
            tempo = tempo.multiply(new BigDecimal(arr.get(i).longValue()));
            result = result.add(tempo);
        }
        return result.toString();
    }

}
