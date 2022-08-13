package silver;

import java.io.*;
import java.util.*;

public class Problem_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> dq = new LinkedList<>();
        String order;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int target = 0;

        for(int i = 0; i < n; i++) {
            order = br.readLine();
            st = new StringTokenizer(order);
            order = st.nextToken();

            if(order.equals("push_front")) {
                target = Integer.parseInt(st.nextToken());
                dq.addFirst(target);
            }

            else if(order.equals("push_back")) {
                target = Integer.parseInt(st.nextToken());
                dq.addLast(target);
            }

            else if(order.equals("pop_front")) {
                try {
                    sb.append(dq.removeFirst() + "\n");
                } catch(NoSuchElementException e) {
                    sb.append(-1 + "\n");
                }
            }

            else if(order.equals("pop_back")) {
                try {
                    sb.append(dq.removeLast() + "\n");
                } catch(NoSuchElementException e) {
                    sb.append(-1 + "\n");
                }
            }

            else if(order.equals("size")) {
                sb.append(dq.size() + "\n");
            }

            else if(order.equals("empty")) {
                if(dq.isEmpty())
                    sb.append(1 + "\n");

                else
                    sb.append(0 + "\n");
            }

            else if(order.equals("front")) {
                if(!dq.isEmpty()) {
                    sb.append(dq.peekFirst() + "\n");
                }

                else {
                    sb.append(-1 + "\n");
                }
            }

            else if(order.equals("back")) {
                if(!dq.isEmpty()) {
                    sb.append(dq.peekLast() + "\n");
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