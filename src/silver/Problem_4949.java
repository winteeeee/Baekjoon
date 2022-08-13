package silver;

import java.io.*;
import java.util.EmptyStackException;
import java.util.Stack;

public class Problem_4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String temp;
        Stack<String> s = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean isNo = false;

        while(true) {
            temp = br.readLine();
            if(temp.equals("."))
                break;
            try {
                for (int i = 0; i < temp.length(); i++) {
                    if (temp.charAt(i) == '(' || temp.charAt(i) == '[')
                        s.push(String.valueOf(temp.charAt(i)));

                    else if (temp.charAt(i) == ')') {
                        if (s.peek().equals("("))
                            s.pop();

                        else {
                            sb.append("no\n");
                            isNo = true;
                            while(!s.isEmpty())
                                s.pop();
                            break;
                        }
                    }

                    else if (temp.charAt(i) == ']') {
                        if (s.peek().equals("["))
                            s.pop();

                        else {
                            sb.append("no\n");
                            isNo = true;
                            while(!s.isEmpty())
                                s.pop();
                            break;
                        }
                    }
                }
            } catch(EmptyStackException e) {
                sb.append("no\n");
                isNo = true;
                while(!s.isEmpty())
                    s.pop();
            }
            if(!s.isEmpty()) {
                sb.append("no\n");
                isNo = true;
                while(!s.isEmpty())
                    s.pop();
            }

            if(!isNo)
                sb.append("yes\n");

            isNo = false;
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
