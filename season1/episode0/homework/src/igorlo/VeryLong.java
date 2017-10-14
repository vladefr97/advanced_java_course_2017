package igorlo;

import java.math.BigInteger;

public class VeryLong {

    private Long[] longs;

    public final static int DEFAULT_CAPACITY = 16;

    //Задаёт числу стартовое значение
    public VeryLong(long number) {
        longs = new Long[DEFAULT_CAPACITY];
        longs[0] = number;
        for (int i = 1; i < DEFAULT_CAPACITY; i++) {
            longs[i] = 0L;
        }
    }

    //Создаёт новое число с заданым запасом Long'ов и нулевым значением
    public VeryLong(int capacity) {
        longs = new Long[capacity];
        for (int i = 0; i < capacity; i++) {
            longs[i] = 0L;
        }
    }

    //Объединяет первые два конструктора. Задаёт размер и стартовое значение.
    public VeryLong(int capacity, long number) {
        longs = new Long[capacity];
        longs[0] = number;
        for (int i = 1; i < capacity; i++) {
            longs[i] = 0L;
        }
    }

    //Возвращает сумму двух VeryLong'ов, не меняя состояния слогаемых
    public static VeryLong getSum(VeryLong one, VeryLong other) {
        VeryLong sum = new VeryLong(one.longs.length);
        sum.set(one);
        sum.add(other);
        return sum;
    }

    //Прибавляет к первому VeryLong'у второй
    public void add(VeryLong other) {

        boolean overflow = sumLongs(other, 0, false);

        for (int i = 1; i < longs.length; i++) {
            overflow = sumLongs(other, i, overflow);
        }

    }

    //Приватный метод, складывающий две ячейки с одинаковым индексом, отслеживает переполнение
    //Используется в сложении
    private boolean sumLongs(VeryLong other, int index, boolean prevOverflow) {
        boolean newOverflow = false;

        long numberOne = longs[index];
        long numberTwo = other.longs[index];
        if (prevOverflow)
            numberTwo++; //DANGEROUS AND MYSTERIOUS PLACE

        if (numberOne < 0 && numberTwo < 0) {
            newOverflow = true;
        } else {
            if ((numberOne < 0 || numberTwo < 0) && (numberOne + numberTwo > 0)) {
                newOverflow = true;
            }
        }

        longs[index] += numberTwo;
        return newOverflow;
    }

    //Копирует в данный VeryLong значение, записанное в числе, указанном в аргументе
    public void set(VeryLong other) {
        for (int i = 0; i < this.longs.length; i++) {
            setLong(other.longs[i], i);
        }
    }

    @Override
    public String toString() {
        BigInteger big = new BigInteger("0");
        for (int i = longs.length - 1; i >= 0; i--) {
            Long currentLong = longs[i];
            if (currentLong != null) {
                big = big.multiply(new BigInteger("18446744073709551616"));
                if (currentLong < 0) {
                    big = big.add(new BigInteger(Long.toString(currentLong + Long.MAX_VALUE + 2)));
                    big = big.add(new BigInteger(Long.toString(Long.MAX_VALUE)));
                } else {
                    big = big.add(new BigInteger(Long.toString(currentLong)));
                }
            }
        }
        return big.toString();
    }

    private void setLong(long newLong, int index) {
        this.longs[index] = newLong;
    }

}
