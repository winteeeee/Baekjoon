package gold;

import java.io.*;
import java.util.LinkedList;

public class Problem_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        FOR1:
        for(int i = 0; i < t; i++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String list = br.readLine();
            LinkedList<Integer> deque = new LinkedList<>();
            boolean isReverse = false;

            int temp = 0;
            for(int j = 0; j < list.length(); j++) {
                if(j != 0) {
                    if(list.charAt(j) == ',' || list.charAt(j) == ']') {
                        if(n > 0)
                            deque.addLast(temp);
                        temp = 0;
                        continue;
                    }

                    temp *= 10;
                    temp += Integer.parseInt(String.valueOf(list.charAt(j)));
                }
            }

            for(int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == 'R') {
                    if(isReverse)
                        isReverse = false;

                    else
                        isReverse = true;
                }

                else {
                    if(deque.size() != 0) {
                        if(isReverse)
                            deque.removeLast();

                        else
                            deque.removeFirst();
                    }

                    else {
                        sb.append("error\n");
                        continue FOR1;
                    }
                }
            }

            sb.append("[");
            int size = deque.size();
            for(int j = 0; j < size; j++) {
                if(isReverse)
                    if(j != size - 1)
                        sb.append(deque.removeLast() + ",");
                    else
                        sb.append(deque.removeLast());

                else
                    if(j != size - 1)
                        sb.append(deque.removeFirst() + ",");
                    else
                        sb.append(deque.removeFirst());
            }

            sb.append("]\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
