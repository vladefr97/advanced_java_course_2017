package main.java.edu.technopolis;

//------Класс, реализующий длинную арифметику
public class BigArithmetic {
    private static long  BASE=(long)1e18;//Основание системы счисления
    private long[] digits=new long[4000];//Размер массива по-умолчанию
    private static int basePow=18;//Степень десяти
    public String str="";//Переменная для реализации метода toString()
    private int curIndex=0;//Текущий индекс элемента массива, соответствующего старшему разряду числа

    public BigArithmetic(Long l, int size){
        digits=new long[size];
        digits[0]=l;
    }

    public void add(BigArithmetic b){
        try {
            int index = 0;
            while (digits[index] != 0 || b.digits[index] != 0) {//Складываем, пока в элементах массива есть числа
                this.digits[index] += b.digits[index];

                if (this.digits[index] >= BASE) {//Если сумма элементов массива боле BASE, обрабатывается переполнение
                    this.digits[index] -= BASE;
                    digits[index + 1]++;
                }
                index++;
            }
            curIndex = index - 1;
        }
        catch (ArrayIndexOutOfBoundsException e){//Если размера массива не хватает для вычисления заданного числа Фибоначчи
            System.out.println("Необходимо увеличить размер массива в классе BigArithmetic");
        }



    }



    @Override
    public String toString() {
        int i=curIndex;
        String check="";
        String newStr="";
        int n;
        while (i>=0)
        {
            n=0;
            check="";
            newStr="";
            if((check+=digits[i]).length()!=basePow&&(i!=curIndex)){
                    n=check.length();
                    for(int j=0;j<basePow-n;j++)
                        newStr+="0";
            }
            str+=newStr+digits[i];
            i--;
        }
        return str;
    }


}
