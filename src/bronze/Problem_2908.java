package bronze;
import java.util.Scanner;

public class Problem_2908 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n1 = sc.next();
		String n2 = sc.next();
		String reverse_n1 = "";
		String reverse_n2 = "";
		
		for(int i = 2; i >= 0; i--) {
			reverse_n1 += n1.charAt(i);
			reverse_n2 += n2.charAt(i);
		}
		
		if(Integer.parseInt(reverse_n1) > Integer.parseInt(reverse_n2) ) {
			System.out.print(reverse_n1);
		}
		
		else {
			System.out.print(reverse_n2);
		}
	}
}