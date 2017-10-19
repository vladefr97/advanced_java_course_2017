package Java;


import java.math.BigInteger;
import java.util.Arrays;

import static java.lang.Long.*;

public class QuestNumberOne {




    public static String fib(int number){
        int size = 4000;
        long[] left = new long[size];
        long[] right = new long[size];
        long[] temp;
        left[0] = 0;
        right[0] = 1;
        for (int i = 0; i < number - 1; i++) {
            temp = Arrays.copyOf(right, size);
            right = res(left,right);
            left = Arrays.copyOf(temp,size);

        }
        // Лепим строку из 4-х лонгов

        StringBuilder str = new StringBuilder();
        for (int i = size - 1; i >= 0 ; i--) {
            String tempStr = Long.toString(right[i],2);
            byte len = (byte)tempStr.length();
            if (len < 63){
                str.append(String.format("%0" + (63 - len) + "d", 0)).append(tempStr);
            }else{
                str.append(tempStr);
            }
        }


        return new BigInteger(str.toString(),2).toString();

    }
    private static long[] res(long[] left, long[] right){
        long[] res = {0,0};
        int i = 0;
        do {
            res = sum(res[1], left[i], right[i]);
            right[i] = res[0];
            i++;
        }while (left[i] != 0 || right[i] != 0 || res[1] == 1 );


        return right;
    }
    private static  long[] sum(long flag, long one, long two){
        long[] sum ={0,0};
        sum[0] = (one + two + flag);

        if ( sum[0] < 0) {
            sum[0] = (sum[0] & MAX_VALUE);
            sum[1] = 1;
        }
        return sum;
    }



}
