package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_12865 {
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] thing = new int[n + 1][2];
        int[][] dp = new int[n + 1][k + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            thing[i][0] = Integer.parseInt(st.nextToken());
            thing[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                if(thing[i][0] <= j && thing[i][1] + dp[i - 1][j - thing[i][0]] > dp[i - 1][j])
                    dp[i][j] = thing[i][1] + dp[i - 1][j - thing[i][0]];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        bw.write(String.valueOf(dp[n][k]));
        bw.flush();
        bw.close();
    }
}
