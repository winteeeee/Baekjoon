package bronze;
import java.util.*;

public class Problem_2775 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for(int i = 0; i < t; i++) {
			int k = sc.nextInt();
			int n = sc.nextInt();
			int[][] arr = new int[k+1][14];
			
			for(int j = 0; j < 14; j++) {
				arr[0][j] = j+1;
			}
			
			for(int j = 1; j < k+1; j++) {
				for(int l = 0; l < 14; l++) {
					for(int p = 0; p <= l; p++) {
						arr[j][l] += arr[j-1][p];
					}
				}
			}
			
			System.out.println(arr[k][n-1]);
		}
	}
}
