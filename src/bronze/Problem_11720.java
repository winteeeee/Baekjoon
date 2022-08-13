package bronze;
import java.util.Scanner;

public class Problem_11720 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		int sum = 0;
		String temp = sc.next();
		
		for(int i = 0; i < count; i++) {
			sum += Character.getNumericValue(temp.charAt(i));
		}
		
		System.out.printf("%d", sum);
	}
}
