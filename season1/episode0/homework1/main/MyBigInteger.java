package advanced_java.homework1.main;

import java.math.BigInteger;

class MyBigInteger implements Cloneable {

    private long[] array;

    MyBigInteger(int n) {
        array = new long[4];
        array[array.length - 1] = n;
    }

    @Override
    public String toString() {
        BigInteger[] bigInteger = new BigInteger[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                bigInteger[i] = BigInteger.valueOf(Long.MAX_VALUE);
                bigInteger[i] = bigInteger[i].pow(array.length - 1 - i);
                bigInteger[i] = bigInteger[i].multiply(BigInteger.valueOf(array[i]));
            }
        }
        BigInteger result = BigInteger.ZERO;
        for (BigInteger aBigInteger : bigInteger) {
            if (aBigInteger != null) {
                result = result.add(aBigInteger);
            }
        }
        return result.toString();
    }

    private void add(int index) {
        long temp = array[index] + 1;
        if (temp < 0) {
            array[index] = Long.MIN_VALUE + array[index] + 1;
            if (index > 0) {
                add(index - 1);
            } else {
                grow(this);
                add(index - 1);
            }
        } else {
            array[index] = temp;
        }
    }

    private void grow(MyBigInteger... myBigIntegers) {
        for (MyBigInteger myBigInteger : myBigIntegers) {
            long[] newArray = new long[myBigInteger.array.length * 2];
            System.arraycopy(myBigInteger.array, 0, newArray, myBigInteger.array.length, myBigInteger.array.length);
            myBigInteger.array = newArray;
        }
    }

    MyBigInteger add(MyBigInteger myBigInteger) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 0 && myBigInteger.array[i] == 0) {
                continue;
            }
            long temp = array[i] + myBigInteger.array[i];
            if (temp < 0) {
                if (i > 0) {
                    array[i] = -Long.MAX_VALUE + array[i] + myBigInteger.array[i];
                    add(i - 1);
                } else {
                    int oldSize = this.array.length;
                    grow(this, myBigInteger);
                    array[oldSize] = -Long.MAX_VALUE + array[oldSize] + myBigInteger.array[oldSize];
                    add(oldSize - 1);
                }
            } else {
                array[i] = temp;
            }
        }
        return this;
    }
}