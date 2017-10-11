package edu.technopolis;

/**
 * Это просто точка входа для первого домашнего задания.
 * Содержит только метод main и ничего кроме. Никакой дполнительной нагрузки не несёт.
 * Является интерфейсом для
 * <ul>
 *     <li>Демонстрации возможности метода main в интрефейсе</li>
 *     <li>Экономии слова public</li>
 * </ul>
 */
public interface EntryPoint {
    static void main(String[] args) {
        int count = 100000;
        FibonacciAlgorithm goodAlgorithm = new GoodFibonacciAlgorithm();
        FibonacciAlgorithm algorithm = new DummyFibonacciAlgorithm();
        String correctResult =  algorithm.evaluate(count);
        System.out.println("correct " + correctResult);
        String myResult =  goodAlgorithm.evaluate(count);

        System.out.println("my result " + myResult);
        System.out.println(correctResult.equals(myResult) ? "Yeah" : "Nope");
    }
}
