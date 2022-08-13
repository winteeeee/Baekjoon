package bronze;
import java.util.Scanner;
import java.util.Stack;

public class Problem_1259 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String temp;

		while(true) {
			temp = sc.next();
			if(temp.equals("0"))
				break;
			
			check(temp);
		}
	}
	
	public static void check(String s) {
		Stack<String> stack = new Stack<>();

		for(int i = 0; i < s.length()/2; i++) {
			stack.add(String.valueOf(s.charAt(i)));
		}

		if(s.length() % 2 == 0) {
			for(int i = s.length()/2; i < s.length(); i++) {
				if(stack.peek().equals(String.valueOf(s.charAt(i)))) {
					stack.pop();
				}

				else {
					System.out.println("no");
					return;
				}
			}

			System.out.println("yes");
		}

		else {
			for(int i = (s.length()/2)+1; i < s.length(); i++) {
				if(stack.peek().equals(String.valueOf(s.charAt(i)))) {
					stack.pop();
				}

				else {
					System.out.println("no");
					return;
				}
			}

			System.out.println("yes");
		}
	}
}
