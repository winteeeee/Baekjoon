package bronze;
import java.util.*;

public class Problem_2609 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int GCD = 1;
		int LCM;
		
		int min = Math.min(n, m);
		for(int i = 1; i <= min; i++) {
			if(n % i == 0 && m % i == 0) {
				GCD = i;
			}
		}
		
		LCM = n*m/GCD;
		System.out.println(GCD);
		System.out.println(LCM);
	}
}
