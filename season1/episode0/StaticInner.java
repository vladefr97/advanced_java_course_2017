package Timur;

public class StaticInner {
        public static long field = 9;
        private int outer_x = 9;

        public void test() {
            Inner inner = new Inner();
            inner.display();
        }

        // внутренний класс
        public class Inner {
            void display() {
                System.out.println("Inner");
            }
        }
      public static class Nested{

            public static void Out(){
                System.out.println("Nested");
            }
        }


}
