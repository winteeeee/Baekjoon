package silver;
import java.util.*;

public class Problem_1978 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nArr = new int[n];
		
		for(int i = 0; i < n; i++) {
			nArr[i] = sc.nextInt();
		}
		
		boolean[] primeNumbers = new boolean[1001];
		
		for(int i = 0; i <= 1000; i++) {
			primeNumbers[i] = true;
		}
		primeNumbers[1] = false;
		
		for(int i = 2; i < 1001; i++) {
			for(int j = 2; i*j < 1001; j++) {
				primeNumbers[i*j] = false;
			}
		}
		
		int count = 0;
		for(int i = 0; i < n; i++) {
			if(primeNumbers[nArr[i]])
				count++;
		}
		
		System.out.print(count);
	}
}
