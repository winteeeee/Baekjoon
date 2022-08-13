package silver;

import java.io.*;
import java.util.*;

public class Problem_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<Integer>();
        String order;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int target = 0;

        for(int i = 0; i < n; i++) {
            order = br.readLine();
            st = new StringTokenizer(order);
            order = st.nextToken();

            if(order.equals("push")) {
                target = Integer.parseInt(st.nextToken());
                q.add(target);
            }

            else if(order.equals("pop")) {
                try {
                    sb.append(q.remove() + "\n");
                } catch(NoSuchElementException e) {
                    sb.append(-1 + "\n");
                }
            }

            else if(order.equals("size")) {
                sb.append(q.size() + "\n");
            }

            else if(order.equals("empty")) {
                if(q.isEmpty())
                    sb.append(1 + "\n");

                else
                    sb.append(0 + "\n");
            }

            else if(order.equals("front")) {
                if(!q.isEmpty()) {
                    sb.append(q.peek() + "\n");
                }

                else {
                    sb.append(-1 + "\n");
                }
            }

            else if(order.equals("back")) {
                if(!q.isEmpty()) {
                    sb.append(target + "\n");
                }

                else {
                    sb.append(-1 + "\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
