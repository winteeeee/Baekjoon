package silver;

import java.io.*;

public class Problem_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n];

        for(int i = 0; i < n; i++)
            stairs[i] = Integer.parseInt(br.readLine());

        int[] dp = new int[n];
        boolean[] isJump = new boolean[n];
        if(n >= 3) {
            dp[0] = stairs[0];
            dp[1] = stairs[0] + stairs[1];
            if (stairs[0] < stairs[1])
                dp[2] = stairs[1] + stairs[2];

            else {
                dp[2] = stairs[0] + stairs[2];
                isJump[2] = true;
            }

            for (int i = 3; i < n; i++) {
                if (!isJump[i - 1]) {
                    if (dp[i - 3] + stairs[i - 1] < dp[i - 2]) {
                        dp[i] = dp[i - 2];
                        isJump[i] = true;
                    }

                    else
                        dp[i] = dp[i - 3] + stairs[i - 1];
                } else {
                    if (dp[i - 2] < dp[i - 1])
                        dp[i] = dp[i - 1];

                    else {
                        dp[i] = dp[i - 2];
                        isJump[i] = true;
                    }
                }
                dp[i] += stairs[i];
            }
        }

        else {
            if(n == 1)
                dp[0] = stairs[0];

            else
                dp[1] = stairs[0] + stairs[1];
        }

        bw.write(String.valueOf(dp[n-1]));
        bw.flush();
        bw.close();
    }
}
