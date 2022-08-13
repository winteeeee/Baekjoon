package silver;

import java.io.*;

public class Problem_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++)
            bw.write(P(Integer.parseInt(br.readLine())) + "\n");

        bw.flush();
        bw.close();
    }

    public static long P(int n) {
        if(n < 6) {
            if(n == 1)
                return 1;

            else if(n == 2)
                return 1;

            else if(n == 3)
                return 1;

            else if(n == 4)
                return 2;

            else
                return 2;
        }

        else {
            long[] dp = new long[n + 1];
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;
            dp[5] = 2;

            for(int  i = 6; i <= n; i++)
                dp[i] = dp[i - 1] + dp[i - 5];

            return dp[n];
        }
    }
}
