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
    static void main(String... args) throws Exception {
//        FibonacciAlgorithm algorithm = new MyFibonacciAlgorithm();
//        for (int i = 1;i<102;i++)
//        System.out.println(algorithm.evaluate(i));
        System.out.println(Long.MAX_VALUE);
        BigLongCase a = new BigLongCase(Long.MAX_VALUE);
        BigLongCase b = new BigLongCase(Long.MAX_VALUE);
        a.sumBig(b);
        System.out.println(a.toString());
    }
}
