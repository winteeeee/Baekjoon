package bronze;
import java.util.*;

public class Problem_3052 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> n = new ArrayList<>();
		
		Loop1 :
		for(int i = 0; i < 10; i++) {
			int temp = sc.nextInt() % 42;
			int nSize = n.size();
			
			for(int j = 0; j < nSize; j++) {
				if(temp == n.get(j)) {
					continue Loop1;
				}
			}
			n.add(temp);
		}
		
		System.out.print(n.size());
	}
}