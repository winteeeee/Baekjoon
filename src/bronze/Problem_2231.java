package bronze;
import java.util.*;

public class Problem_2231 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		String temp = "";
		int temp2 = 0;
		for(int i = 1; i <= n; i++) {
			temp = String.valueOf(i);
			for(int j = 0; j < temp.length(); j++) {
				temp2 += Integer.parseInt(String.valueOf(temp.charAt(j))); 
			}
			temp2 += i;

			if(temp2 == n) {
				System.out.print(i);
				return;
			}
			
			temp2 = 0;
		}
		
		System.out.print(temp2);
	}
}
