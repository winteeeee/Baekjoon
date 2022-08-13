package silver;
import java.util.Scanner;

public class Problem_1929 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		boolean[] arr = new boolean[n+1];
		
		for(int i = 0; i <= n; i++) {
			if(i >= m) {
				arr[i] = true;
			}
			
			else {
				arr[i] = false;
			}
		}
		
		arr[1] = false;
		
		for(int i = 2; i < n; i++) {
			for(int j = 2; i*j <= n; j++) {
				arr[i*j] = false;
			}
		}
		
		for(int i = 0; i <= n; i++) {
			if(i >= m && arr[i]) {
				System.out.println(i);
			}
		}
	}
}
