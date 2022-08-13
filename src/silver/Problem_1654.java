package silver;

import java.util.Scanner;

public class Problem_1654 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int n = sc.nextInt();
		double[] lines = new double[k];
		
		for(int i = 0; i < k; i++) {
			lines[i] = sc.nextInt();
		}
		
		double temp = 1;
		long division = 0;
		
		for(double i = 2; i != 1; i = 1 + ((i - 1) / 2)) {
			temp = check(k, n, lines, division, temp, i);
		}
		
		System.out.print((int)temp);
	}
	
	public static double check(int k, int n, double[] lines, long division, double temp, double m) {
		while(true) {
			for(int i = 0; i < k; i++) {
				division += lines[i] / temp;
			}
			
			if(division < n) {
				temp /= m;
				return temp;
			}
			
			else {
				temp *= m;
				division = 0;
			}
		}
	}
}
