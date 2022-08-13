package bronze;
import java.util.Scanner;

public class Problem_2577 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n1 = sc.nextInt();
		int n2 = sc.nextInt();
		int n3 = sc.nextInt();
		String value = String.valueOf(n1*n2*n3);
		int[] count = new int[10];
		
		for(int i = 0; i < value.length(); i++) {
			count[Integer.parseInt(String.valueOf(value.charAt(i)))]++;
		}
		
		for(int i = 0; i < count.length; i++) {
			System.out.printf("%d\n", count[i]);
		}
	}
}