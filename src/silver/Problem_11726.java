package silver;

import java.io.*;

public class Problem_11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];

        for(int i = 1; i < dp.length; i++) {
            if(i > 2)
                dp[i] = (dp[i-1] + dp[i-2]) % 10007;

            else
                dp[i] = i;
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
        bw.close();
    }
}
