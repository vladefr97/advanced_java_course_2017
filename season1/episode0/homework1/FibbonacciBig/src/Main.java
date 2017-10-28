import algo.BigFibonacci;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String answer = s.nextLine();
		int a = Integer.parseInt(answer);
		System.out.println(BigFibonacci.findIterative(a));
    }
}
