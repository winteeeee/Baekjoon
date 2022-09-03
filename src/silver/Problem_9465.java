package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_9465 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][n];

            for(int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k = 0; k < n; k++)
                    sticker[j][k] = Integer.parseInt(st.nextToken());
            }

            if(n == 1)
                bw.write(Math.max(sticker[0][0], sticker[1][0]) + "\n");

            else if(n == 2)
                bw.write(Math.max(sticker[1][0] + sticker[0][1], sticker[0][0] + sticker[1][1]) + "\n");

            else {
                int[][] dp = new int[2][n];
                dp[0][0] = sticker[0][0];
                dp[1][0] = sticker[1][0];
                dp[0][1] = dp[1][0] + sticker[0][1];
                dp[1][1] = dp[0][0] + sticker[1][1];

                for(int j = 2; j < n; j++)
                    for (int k = 0; k < 2; k++)
                        dp[k][j] = sticker[k][j] + Math.max(dp[1 - k][j - 1], dp[1 - k][j - 2]);

                bw.write(Math.max(dp[0][n - 1], dp[1][n - 1]) + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
