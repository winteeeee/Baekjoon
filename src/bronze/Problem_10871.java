package bronze;
import java.util.Scanner;

public class Problem_10871 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max = sc.nextInt();
		int standard = sc.nextInt();
		int[] a = new int[max];
		
		for(int i = 0; i < max; i++) {
			a[i] = sc.nextInt();
		}
		
		for(int i = 0; i < max; i++) {
			if(a[i] < standard)
				System.out.printf("%d ", a[i]);
		}
	}
}