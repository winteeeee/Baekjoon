package silver;
import java.util.Scanner;

public class Problem_1436 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		System.out.print(shom(n));
	}
	
	public static int shom(int n) {
		int[] arr = new int[10001];
		arr[1] = 666;
		int temp = 1666;
		
		for(int i = 2; i < 10001; temp++) {
			if(check(String.valueOf(temp))) {
				arr[i] = temp;
				i++;
			}
		}
		
		return arr[n];
	}
	
	public static boolean check(String s) {
		for(int i = 0; i < s.length()-2; i++) {
			if(s.charAt(i) == '6' && s.charAt(i+1) == '6' && s.charAt(i+2) == '6') {
				return true;
			}
		}
		
		return false;
	}
}
