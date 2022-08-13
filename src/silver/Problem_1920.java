package silver;
import java.util.Arrays;
import java.util.Scanner;

public class Problem_1920 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nArray = new int[n];
		
		for(int i = 0; i < n; i++) {
			nArray[i] = sc.nextInt();
		}
		Arrays.sort(nArray);
		
		int m = sc.nextInt();
		for(int i = 0; i < m; i++) {
			if(Arrays.binarySearch(nArray, sc.nextInt()) > -1) {
				System.out.println("1");
			}
			
			else {
				System.out.println("0");
			}
		}
	}
}
