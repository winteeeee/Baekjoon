package silver;
import java.util.*;

public class Problem_1966 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new LinkedList<>();
		int t = sc.nextInt();
		int n, target;
		int max = 0;
		
		for(int i = 0; i < t; i++) {
			n = sc.nextInt();
			target = sc.nextInt();
			for(int j = 0; j < n; j++) {
				int temp = sc.nextInt();
				
				if(j == target) 
					temp = temp*10+1;
				
				else
					temp *= 10;
			
				if(temp/10 > max) {
					max = temp/10;
				}
				q.add(temp);
			}
			
			int count = 0;
			while(true) {
				int temp2 = q.peek();
				if(temp2/10 < max) {
					q.add(q.remove());
				}
				
				else {
					count++;
					if((temp2 % 10) == 1) {
						while(q.size() != 0) 
							q.remove();
						
						System.out.println(count);
						count = 0;
						break;
					}
					q.remove();
					
					max = 0;
					for(int j = 0; j < q.size(); j++) {
						if(q.peek()/10 > max) {
							max = q.peek()/10;
						}
						q.add(q.remove());
					}
				}
			}
			max = 0;
		}
	}
}
