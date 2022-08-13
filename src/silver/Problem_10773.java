package silver;

import java.io.*;
import java.util.Stack;

public class Problem_10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> s = new Stack<>();
        int k = Integer.parseInt(br.readLine());

        for(int i = 0; i < k; i++) {
            int temp = Integer.parseInt(br.readLine());
            if(temp != 0) {
                s.push(temp);
            }

            else
                s.pop();
        }

        int sum = 0;
        while(!s.isEmpty()) {
            sum += s.pop();
        }
        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    }
}
