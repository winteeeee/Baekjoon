package silver;
import java.util.*;

public class Problem_2164 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new LinkedList<>();
		int n = sc.nextInt();
		
		for(int i = 1; i <= n; i++) {
			q.add(i);
		}
		
		while(q.size() != 1) {
			q.remove();
			q.add(q.remove());
		}
		
		System.out.print(q.peek());
	}
}
