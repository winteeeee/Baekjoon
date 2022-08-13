package bronze;
import java.util.*;

public class Problem_10952 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();

		while(a != 0 && b != 0) {
			System.out.printf("%d\n", a+b);
			a = sc.nextInt();
			b = sc.nextInt();
		}
	}
}