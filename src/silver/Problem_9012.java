package silver;

import java.util.Scanner;
import java.util.Stack;

public class Problem_9012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> s = new Stack<>();
        s.push("#");
        int t = sc.nextInt();
        String str;
        boolean isNo = false;

        for(int i = 0; i < t; i++, isNo = false) {
            str = sc.next();

            for(int j = 0; j < str.length(); j++) {
                if(str.charAt(j) == '(')
                    s.push(String.valueOf(str.charAt(j)));

                else if(str.charAt(j) == ')') {
                    if(s.peek().equals("("))
                        s.pop();

                    else {
                        isNo = true;
                        break;
                    }
                }
            }

            if(s.size() != 1) {
                isNo = true;
                while(s.size() != 1)
                    s.pop();
            }

            if(isNo)
                System.out.println("NO");

            else
                System.out.println("YES");
        }
    }
}
