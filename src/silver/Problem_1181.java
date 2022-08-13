package silver;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_1181 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] words = new String[n];
		int[][] check = new int[n][2];
		
		for(int i = 0; i < n; i++) {
			words[i] = sc.next();
		}
		Arrays.sort(words);
		
		for(int i = 0; i < n; i++) {
			check[i][0] = words[i].length();
			check[i][1] = i;
		}
		Arrays.sort(check, new Comparator<int[]>() {
			public int compare(int[] c1, int[] c2) {
				return c1[0] - c2[0];
			}
		});
		
		for(int i = 0; i < n; i++) {
			if(i == 0)
				System.out.println(words[check[i][1]]);
			if(i != 0 && !words[check[i][1]].equals(words[check[i-1][1]]))
				System.out.println(words[check[i][1]]);
		}
	}
}
