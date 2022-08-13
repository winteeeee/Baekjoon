package bronze;
import java.util.*;

public class Problem_1157 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine().toUpperCase();
		int[] arr = new int[26];
		
		for(int i = 0; i < str.length(); i++) {
			arr[str.charAt(i)-65] += 1;
		}
		
		int max = arr[0];
		int index = 0;
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
				index = i;
			}
		}

		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == max) {
				count++;
				if(count == 2) {
					System.out.print("?");
					return;
				}
			}
		}

		System.out.print((char)(65+index));
	}
}