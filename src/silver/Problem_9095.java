package silver;

import java.io.*;

public class Problem_9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i ++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(sum123(n)+"\n"));
        }
        bw.flush();
        bw.close();
    }

    public static int sum123(int n) {
        int[] memo = new int[12];
        memo[0] = 1;
        memo[1] = 2;
        memo[2] = 4;

        for(int i = 3; i <= n; i++) {
            memo[i] = memo[i-1] + memo[i-2] + memo[i-3];
        }

        return memo[n-1];
    }
}
