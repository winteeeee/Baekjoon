package silver;
import java.util.*;

public class Problem_2108 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		int medianValue, mode, range;
		double arithmeticMean = 0;
		
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			arithmeticMean += arr[i];
		}
		arithmeticMean /= n;
		Arrays.sort(arr);
		medianValue = arr[n/2];
		
		ArrayList<Integer> frequency = new ArrayList<>();
		ArrayList<Integer> number = new ArrayList<>();
		int count = 0;
		for(int i = 0; i < n-1; i++) {
			if(arr[i] == arr[i+1]) {
				count++;
			}

			else {
				frequency.add(count);
				number.add(arr[i]);
				count = 0;
			}
			
			if(arr[i] == arr[i+1] && i == n-2) {
				frequency.add(count);
				number.add(arr[i]);
			}
		}
		
		if(arr.length == 1) {
			frequency.add(0);
			number.add(arr[0]);
		}
		
		mode = number.get(0);
		count = 0;
		int max = Integer.MIN_VALUE;
		int size = frequency.size();
		
		for(int i = 0; i < size; i++) {
			if(frequency.get(i).compareTo(max) > 0) {
				max = frequency.get(i);
				mode = number.get(i);
				count = 0;
			}
			
			else if(frequency.get(i).compareTo(max) == 0 && count == 0) {
				mode = number.get(i);
				count++;
			}
		}
		
		range = arr[n-1] - arr[0];
		
		System.out.println(Math.round((arithmeticMean*10)/10));
		System.out.println(medianValue);
		System.out.println(mode);
		System.out.println(range);
	}
}
