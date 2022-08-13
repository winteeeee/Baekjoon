package bronze;
import java.util.Scanner;

public class Problem_2475 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int LENGTH = 5;
		int[] numbers = new int[LENGTH];
		
		for(int i = 0; i < LENGTH; i++) {
			numbers[i] = sc.nextInt();
		}
		
		int result = 0;
		for(int i = 0; i < LENGTH; i++) {
			result += Math.pow(numbers[i], 2);
		}
		
		System.out.printf("%d", result%10);
	}
}