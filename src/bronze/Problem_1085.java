package bronze;

import java.util.Scanner;

public class Problem_1085 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		int w = sc.nextInt();
		int h = sc.nextInt();
		
		int result = Math.min(Math.min(x, Math.abs(w-x)), Math.min(y, Math.abs(h-y)));
	
		System.out.print(result);
	}
}
