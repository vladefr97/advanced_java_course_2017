package edu.technopolis;

public class LongNumber {

    private long[] numbers;
    private int length;
    private int bitCount;

    private static final long MASK_EIGHT = 0b10001000_10001000_10001000_10001000_10001000_10001000_10001000_10001000L;
    private static final long MASK_THREE = 0b00110011_00110011_00110011_00110011_00110011_00110011_00110011_00110011L;

    LongNumber(int length) {
        numbers = new long[length];
        this.length = length;
    }

    LongNumber(int length, long value) {
        numbers = new long[length];
        numbers[length - 1] = value;
        this.length = length;
    }

    void setNumber(int i, long number) {
        if (length - i - 1 < 0) {
            return;
        }
        numbers[length - i - 1] = number;
    }

    long getNumber(int i) {
        return (length - i - 1 >= 0) ? numbers[length - i - 1] : 0;
    }

    private void setFirstBit(long bit) {
        numbers[length - 1] &= ~1L;
        numbers[length - 1] |= bit;
    }

    private long getBit(int i) {
        return (length - (i / 64) - 1 < 0) ? 0 : (numbers[length - (i / 64) - 1] >> (i % 64)) & 1L;
    }

    int getLength() {
        return length;
    }

    private void leftOffset(long bit) {
        if (length == 0) {
            return;
        }
        for (int i = 0; i < length - 1; i++) {
            numbers[i] = numbers[i] << 1;
            long temp = numbers[i + 1] & Long.MIN_VALUE;
            numbers[i] |= (temp >>> 63);
        }
        numbers[length - 1] = numbers[length - 1] << 1;
        setFirstBit(bit);
    }

    //подсчет значащих битов в числе
    private void countBits() {
        int i = length * 64 - 1;
        while (i >= 0 && ((numbers[length - (i / 64) - 1] >> (i % 64)) & 1L) == 0) {
            i--;
        }
        bitCount = (i == -1) ? 0 : (i + 1);
    }

    @Override
    public String toString() {
        //Определяем количество битов для двоично-десятичного кода
        int tetradsBitCount = (int) Math.ceil((length * 65 * 0.3));
        //Тетрады храним в LongNumber
        LongNumber tetrads = new LongNumber(tetradsBitCount / 16 + Math.min(1, tetradsBitCount % 16));
        //Прибавляем к каждой тетраде тройку
        for (int i = 0; i < tetrads.length; i++) {
            tetrads.numbers[i] = MASK_THREE;
        }
        //главный цикл
        for (int i = length * 64; i >= 0; i--) {
            //определяем необходимость коррекции
            LongNumber correctionPlus = new LongNumber(tetrads.length);
            LongNumber correctionMinus = new LongNumber(tetrads.length);
            for (int j = 0; j < tetrads.length; j++) {
                long temp = tetrads.numbers[j] & MASK_EIGHT;
                //сначала работаем с correctionPlus
                temp = temp >>> 2;
                temp |= temp >>> 1;
                correctionPlus.numbers[j] = temp;
                //работаем с correctionMinus
                temp = (tetrads.numbers[j] & MASK_EIGHT) ^ MASK_EIGHT;
                temp = temp >>> 2;
                temp |= temp >>> 1;
                correctionMinus.numbers[j] = temp;
            }
            //сдвигаем влево и вталкиваем старший бит из числа
            tetrads.leftOffset(getBit(i + (i / 63)));
            //корректируем
            for (int j = 0; j < tetrads.length; j++) {
                tetrads.numbers[j] += correctionPlus.numbers[j] - correctionMinus.numbers[j];
            }
        }
        //отнимаем избыточную тройку
        for (int i = 0; i < tetrads.length; i++) {
            tetrads.numbers[i] -= MASK_THREE;
        }
        //вывод
        tetrads.countBits();
        StringBuilder s = new StringBuilder();
        int res = 0;
        for (int i = tetrads.bitCount - 1; i >= 0; i--) {
            res += (tetrads.getBit(i) * Math.pow(2, i % 4));
            if (i % 4 == 0) {
                s.append(Integer.toString(res));
                res = 0;
            }
        }
        //обрабатываем отдельно случай, если значащих битов нет
        if (tetrads.bitCount == 0) {
            s = new StringBuilder("0");
        }
        return s.toString();
    }
}
