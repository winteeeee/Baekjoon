package silver;

import java.io.*;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> s = new Stack<>();
        String order;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            order = br.readLine();
            st = new StringTokenizer(order);
            order = st.nextToken();

            if(order.equals("push")) {
                int target = Integer.parseInt(st.nextToken());
                s.push(target);
            }

            else if(order.equals("pop")) {
                try {
                    sb.append(s.pop() + "\n");
                } catch(EmptyStackException e) {
                    sb.append(-1 + "\n");
                }
            }

            else if(order.equals("size")) {
                sb.append(s.size() + "\n");
            }

            else if(order.equals("empty")) {
                if(s.isEmpty())
                    sb.append(1 + "\n");

                else
                    sb.append(0 + "\n");
            }

            else if(order.equals("top")) {
                try {
                    sb.append(s.peek() + "\n");
                } catch(EmptyStackException e) {
                    sb.append(-1 + "\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
