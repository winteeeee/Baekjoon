package bronze;
import java.util.Scanner;

public class Problem_1152 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Scanner sc2 = new Scanner(str);
		int count = 0;
		
		while(sc2.hasNext()) {
			if(!sc2.next().equals(" "))
				count++;
		}
		
		System.out.print(count);
	}
}