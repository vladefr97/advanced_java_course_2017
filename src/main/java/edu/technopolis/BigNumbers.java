package edu.technopolis;

public class BigNumbers {



    private int SIZE = 100000;
    private final long maxLong = 999999999999999999L;
    private long[] myNumberArr;

    public BigNumbers() {
        myNumberArr = new long[SIZE];
        for (long elem : myNumberArr) {
            elem = 0;
        }

    }

    public BigNumbers(long number) {
        myNumberArr = new long[SIZE];
        for (long elem : myNumberArr) {
            elem = 0;
        }
        myNumberArr[0] = number;
    }

    public int getSize() {
        return SIZE;
    }

    public long getElement(int index) {
        return myNumberArr[index];
    }



    public void inputNumber(BigNumbers number) {
        SIZE = number.getSize();
        myNumberArr = new long[SIZE];
        for (int i=0; i< SIZE; i++) {
            myNumberArr[i] = number.getElement(i);
        }
    }

    public void print() {
        System.out.println(toString());
    }


    @Override
    public String toString() {

        String result = "";
        for (int i= SIZE -1; i>=0; i--) {
            if ( myNumberArr[i] > 0){
                result += myNumberArr[i];
            }
        }
        return result;
    }




    public void add(BigNumbers number) { //usual add
        for (int i=0; i<number.getSize(); i++) {
            addElement(number, i);
        }
    }


    private void addElement(BigNumbers number, int index) {

        if (maxLong - number.getElement(index) < myNumberArr[index]) { //overflow
            //При переносе разряда используем формулу A + B = (A - (maxLong - B)) - 1
            myNumberArr[index] = myNumberArr[index] - (maxLong - number.getElement(index)) - 1;

            try {
                rankTransfer(index + 1, number);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("error - ovf");
            }
        } else {
            myNumberArr[index]+=number.getElement(index);// without ovf
        }
    }



    private void rankTransfer(int index, BigNumbers number) {
        if (index == SIZE) {
            increaseCapasity(number);
        }
        if (maxLong  == myNumberArr[index]) {
            myNumberArr[index] = 0;

            try {
                rankTransfer(index + 1, number);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("error - ovf");
            }

        } else {
            // all is ok
            myNumberArr[index]++;
        }
    }


    private void increaseCapasity(BigNumbers number) {
        long[] temp = new long[number.SIZE];

        //System.arraycopy(number.myNumberArr,0,temp,0,number.SIZE); // source, pos_S, dest, pos_D, amOfCopElem

        for (int i=0; i<number.SIZE; i++) {
            temp[i] = number.myNumberArr[i];
        }

        number.myNumberArr = new long[number.SIZE + 1];

       // System.arraycopy(temp,0,number.myNumberArr,0,number.SIZE);

        for (int i=0; i<number.SIZE; i++) {
            number.myNumberArr[i] = temp[i];
        }
        number.SIZE++;
    }
}