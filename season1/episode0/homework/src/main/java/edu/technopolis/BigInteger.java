package edu.technopolis;


import java.util.ArrayList;

/**
 * Created by alexandr on 22.09.17.
 */
public class BigInteger {

    private ArrayList<Long> integer;

    BigInteger(Long val) {
        integer = new ArrayList<>();
        integer.add(val);
    }


    private Long get(int index) {
        return integer.get(index);
    }

    public int length() {
        return integer.size();
    }

    public void add(BigInteger val) {
        boolean residual = false;
        for (int i = 0; (i < Math.max(this.length(), val.length())) || residual; i++) {
            if (i == this.length()) {
                integer.add(0L);
            }
            Long tmp = integer.get(i);
            tmp += (residual ? 1 : 0) + (i < val.length() ? val.get(i) : 0);
            if (tmp < 0) {
                residual = true;
                tmp = Long.MAX_VALUE + (tmp + 1);
            } else {
                residual = false;
            }
            integer.set(i, tmp);
        }
    }

    public String toString() {
        java.math.BigInteger res = java.math.BigInteger.valueOf(integer.get(0));
        for (int i = 1; i < length(); i++) {
            java.math.BigInteger tmp = java.math.BigInteger.valueOf(1);

            for (int j = 0; j < i; j++) {
                tmp = tmp.multiply(java.math.BigInteger.valueOf(Long.MAX_VALUE).add(java.math.BigInteger.ONE));
            }
            res = res.add(tmp.multiply(java.math.BigInteger.valueOf(integer.get(i))));
        }
        return res.toString();
    }

}
