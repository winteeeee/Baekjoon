package silver;

import java.io.*;
import java.util.*;

public class Problem_1874 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> s = new Stack<>();
		ArrayList<String> list = new ArrayList<>();
		int n = Integer.parseInt(br.readLine());
		int[] progression = new int[n];
		boolean[] usedNumber = new boolean[n+1];
		
		for(int i = 0; i < n; i++) {
			progression[i] = Integer.parseInt(br.readLine());
			usedNumber[i] = false;
		}
		
		int startPosition = 1;
		for(int i = 0; i < n; i++) {
			for( ; startPosition <= progression[i]; startPosition++) {
				if(!usedNumber[startPosition]) {
					s.push(startPosition);
					list.add("+");
					usedNumber[startPosition] = true;
				}

			}

			if(s.peek() == progression[i]) {
				s.pop();
				list.add("-");
			}

			else {
				System.out.print("NO");
				return;
			}
		}

		int listSize = list.size();
		for(int i = 0; i < listSize; i++) {
			System.out.println(list.get(i));
		}
	}
}
