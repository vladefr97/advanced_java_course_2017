package edu.technopolis;

import com.sun.prism.shader.Solid_ImagePattern_Loader;

import java.math.BigInteger;
import java.util.Scanner;

public class NormalFibonacciAlgorithm implements FibonacciAlgorithm {
    public static final long BASE = (long)(Math.pow(2,63));
    public static final int SIZE = 10000; //up to 364

    public String evaluate(int index) {
        VeryLong fib = new VeryLong();
        fib.clone(VeryLong.fibonacci(index)); //получаем это число VeryLong
        String str = new String(fib.makeString());
        BigInteger result = new BigInteger(str,2);
        return result.toString();
    }

    //класс, реализующий длинную арифметику. Использует 63/64 битов у Long
    public static class VeryLong{
        public long[] parts;

        //Конструктор по умолчанию
        public VeryLong() {
            parts = new long[SIZE];
            for(int i=0;i<SIZE;i++){ parts[i]=0;}
        }

        //Конструктор для long аргумента
        public VeryLong(Long initNum) {
            parts = new long[SIZE];
            for(int i=1;i<SIZE;i++){parts[i]=0;}
            parts[0]=initNum;
        }

        //добавляет к текущему числу another
        public void add(VeryLong another) {
            for(int i=0;i<SIZE;i++) {
                this.parts[i]+=another.parts[i];
                if (this.parts[i]<0) {
                    this.parts[i+1]+=1;
                    this.parts[i]-=BASE+1;
                }
            }
        }

        //возвращает index'ное число ряда Фибоначчи типа VeryLong
        public static VeryLong fibonacci(int index) {
            VeryLong fib1 =new VeryLong(1l);
            VeryLong fib2 =new VeryLong(1l);
            VeryLong temp =new VeryLong(0l);
            for (int i=3;i<=index; i++) {
                temp.clone(fib2);
                fib2.add(fib1);
                fib1.clone(temp);
            }
            return fib2;
        }

        //вернет строку, представляющую число в двочином вие
        public StringBuilder makeString() {

            StringBuilder str = new StringBuilder();
            int i=SIZE-1; //берем последний элемент массива
            while(this.parts[i]==0) i--;//если в нем ноль, то берем следующий, пока не найдем первый ненулевой
            for (;i>=0;i--) {
                //записываем число в текущей ячейке в двоичном виде
                StringBuilder temp1 = new StringBuilder();
                temp1.append(Long.toString(this.parts[i],2));
                //добавляем необходимые ведущие нули
                int len=(63-temp1.length());
                for(int j=0;j<len;j++){
                    str.append('0');
                }
                str.append(temp1);
            }
            return str;
        }

        public void clone(VeryLong another) {
            for (int i=0;i<SIZE;i++) this.parts[i]=another.parts[i];
            return;
        }
    }
}