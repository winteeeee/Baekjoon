package bronze;

import java.util.*;

public class Problem_10951 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a;
		int b;

		try {
			while(sc.hasNextLine()) {
				a = sc.nextInt();
				b = sc.nextInt();
				System.out.printf("%d\n", a+b);
			}
		} catch(NoSuchElementException e) {
			return;
		}
	}
}