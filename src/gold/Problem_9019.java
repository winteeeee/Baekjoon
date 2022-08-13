package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_9019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Queue<String> q = new LinkedList<>();
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[a];
            q.add(String.valueOf(a));
            q.add("");
            String code;

            while(true) {
                int n = Integer.parseInt(q.remove());
                code = q.remove();

                if(n == b) {
                    while(!q.isEmpty())
                        q.remove();
                    break;
                }

                if(2 * n > 9999) {
                    q.add(String.valueOf(2 * n % 10000));
                    q.add(code + "D");
                }

                else {
                    q.add(String.valueOf(2 * n));
                    q.add(code + "D");
                }

                if(n == 0) {
                    q.add("9999");
                    q.add(code + "S");
                }

                else {
                    q.add(String.valueOf(n-1));
                    q.add(code + "S");
                }

                String temp = "";
                if(n < 1000 && n >= 100) {
                    sb.append("0");
                    sb.append(String.valueOf(n).charAt(0));
                    sb.append(String.valueOf(n).charAt(1));
                    sb.append(String.valueOf(n).charAt(2));
                    temp = sb.toString();
                }

                else if(n < 100 && n >= 10) {
                    sb.append("0");
                    sb.append("0");
                    sb.append(String.valueOf(n).charAt(0));
                    sb.append(String.valueOf(n).charAt(1));
                    temp = sb.toString();
                }

                else if(n < 10) {
                    sb.append("0");
                    sb.append("0");
                    sb.append("0");
                    sb.append(String.valueOf(n).charAt(0));
                    temp = sb.toString();
                }

                else
                    temp = String.valueOf(n);

                sb.delete(0, 5);

                sb.append(temp.charAt(1));
                sb.append(temp.charAt(2));
                sb.append(temp.charAt(3));
                sb.append(temp.charAt(0));

                q.add(sb.toString());
                q.add(code + "L");
                sb.delete(0, 5);

                sb.append(temp.charAt(3));
                sb.append(temp.charAt(0));
                sb.append(temp.charAt(1));
                sb.append(temp.charAt(2));

                q.add(sb.toString());
                q.add(code + "R");
                sb.delete(0, 5);
            }
            bw.write(code + "\n");
        }
        bw.flush();
        bw.close();
    }
}
