package silver;

import java.io.*;

public class Problem_11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+3];

        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 5;
        for(int i = 4; i < dp.length; i++) {
            dp[i] = (dp[i-1] + dp[i-2] * 2) % 10007;
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
        bw.close();
    }
}
