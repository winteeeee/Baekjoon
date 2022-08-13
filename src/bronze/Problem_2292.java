package bronze;
import java.util.*;

public class Problem_2292 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		if(n == 1) {
			System.out.print("1");
		}

		else {
			int temp = 1;
			for(int i = 1; true; i++) {
				temp += 6*i;
				
				if(temp >= n) {
					System.out.print(i+1);
					break;
				}
			}
		}
	}
}
