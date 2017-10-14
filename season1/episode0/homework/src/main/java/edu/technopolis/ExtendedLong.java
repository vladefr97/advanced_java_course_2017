package edu.technopolis;

import java.math.BigInteger;
import java.util.Arrays;

class ExtendedLong {
    private static final int MAX_CAPACITY = 4000;

    private long[] mLongs;

    ExtendedLong(long value) {
        mLongs = new long[MAX_CAPACITY];
        Arrays.stream(mLongs).forEach(x -> x = 0L);
        mLongs[0] = value;
    }

    void sum(ExtendedLong second) {
        byte[] overflowFlags = new byte[MAX_CAPACITY];

        // if sum is negative then 0-th OF set
        overflowFlags[0] = mLongs[0] + second.mLongs[0] < 0
                ? (byte) 1
                : (byte) 0;

        /*
         if sum is negative
         or if previous sum was negative and this sum is (will become) negative with extra 1
         then i-th OF set
        */
        for (int i = 1; i < MAX_CAPACITY; i++) {
            overflowFlags[i] = (mLongs[i] + second.mLongs[i] < 0) ||
                    (mLongs[i] + second.mLongs[i] + 1 < 0 && overflowFlags[i - 1] == 1)
                    ? (byte) 1
                    : (byte) 0;
        }

        for (int i = 0; i < MAX_CAPACITY; i++) {
            // we have last OF set so we need to add extra 1 to current element
            if (i > 0 && overflowFlags[i - 1] == 1) {
                mLongs[i] += 1;
            }

            if (overflowFlags[i] == 1) {
                // adding only extra part that does not fit
                // here we're loosing one 1, so we need to consider it when printing out
                mLongs[i] += second.mLongs[i] + Long.MIN_VALUE;
             }
            else {
                // simply adding elements up
                mLongs[i] += second.mLongs[i];
            }
        }
    }
    
    @Override
    public String toString() {
        BigInteger result = new BigInteger("0");

        for (int i = 0; i < MAX_CAPACITY; i++) {
            // BigInteger representation of i-th long
            BigInteger num = new BigInteger(String.valueOf(mLongs[i]));

            for (int j = 0; j < i; j++) {
                // Consider "weight" of i-th element, it shows how many times OF occurred.
                // Since we lost one 1 above, we need to multiply by Long.MAX_VALUE + 1 -> Long.MIN_VALUE
                num = num.multiply(BigInteger.valueOf(Long.MIN_VALUE));
            }

            // Absolute value for BigInteger
            num = num.toString().charAt(0) == '-'
                    ? num.multiply(BigInteger.valueOf(-1))
                    : num;

            result = result.add(num);

        }

        return result.toString();
    }
}
