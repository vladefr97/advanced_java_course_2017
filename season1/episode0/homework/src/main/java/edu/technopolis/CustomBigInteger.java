package edu.technopolis;

import java.math.BigInteger;
import java.util.Arrays;

public class CustomBigInteger {

    /**
     * Число в системе счисления с основанием {@code Long.MAX_VALUE + 1},
     * представленное в виде {@code long[]}.
     */
    private long[] number;

    /**
     * Размер массива {@code number}.
     */
    private int size;

    /**
     * Стандартный размер массива {@code number}.
     * 4 лонга хватает примерно на 350 чисел Фибоначчи.
     */
    private static final int DEFAULT_CAPACITY = 4;

    /**
     * Стандартный конструктор, создает массив {@code number} с размером по дефолту.
     */
    public CustomBigInteger() {
        number = new long[DEFAULT_CAPACITY];
        size = DEFAULT_CAPACITY;
    }

    /**
     * Конструктор с заданием произвольного размера массива {@code number}.
     * @param size размер массива {@code number}, т.е. число лонгов.
     */
    public CustomBigInteger(int size) {
        number = new long[size];
        this.size = size;
    }

    /**
     * Конструктор с заданием начального значения числа.
     * @param value начальное значение.
     */
    public CustomBigInteger(long value) {
        number = new long[DEFAULT_CAPACITY];
        this.size = DEFAULT_CAPACITY;
        number[0] = value;
    }

    /**
     * Сумма данного экземпляра и {@code numberToAdd}.
     * @param numberToAdd второе слагаемое.
     * @return сумма двух чисел.
     */
    public CustomBigInteger add(CustomBigInteger numberToAdd) {

        CustomBigInteger result = new CustomBigInteger(Math.max(this.size, numberToAdd.size));

        for (int i = 0; i < result.size - 1; i++) {
            result.number[i] += this.number[i] + numberToAdd.number[i];
            if (result.number[i] < 0) {
                result.number[i] -= Long.MIN_VALUE;
                result.number[i + 1]++;
            }
        }

        //частный случай для последнего разряда: если он переполняется, то массив увеличивается
        int lastIndex = result.size - 1;
        result.number[lastIndex] += this.number[lastIndex] + numberToAdd.number[lastIndex];
        if (result.number[lastIndex] < 0) {
            result.number[lastIndex] -= Long.MIN_VALUE;
            result.grow();
            numberToAdd.grow();
            result.number[lastIndex + 1]++;
        }

        return result;
    }

    /**
     * Переводит число в строку с помощью {@link java.math.BigInteger}.
     * @return строковое значение числа.
     */
    @Override
    public String toString() {

        //основание системы счисления
        final BigInteger BASE = BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE);

        //множитель системы счисления в данный момент
        BigInteger currentBaseFactor = BigInteger.ONE;

        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < size; i++) {
            result = result.add(BigInteger.valueOf(number[i]).multiply(currentBaseFactor));
            currentBaseFactor = currentBaseFactor.multiply(BASE);
        }

        return result.toString();
    }

    /**
     * Увеличивает массив {@code number} на четверть.
     */
    private void grow() {
        size += size / 4;
        number = Arrays.copyOf(number, size);
    }
}
