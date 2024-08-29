package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_10710 {
    static int n;
    static int m;
    static int[] d;
    static int[] c;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        d = new int[n];
        c = new int[m];
        for (int i = 0; i < n; i++)
            d[i] = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++)
            c[i] = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= m; j++) {
                int addFatigueDegree = d[i - 1] * c[j - 1];
                int minValue = Integer.MAX_VALUE;
                for (int k = i - 1; k < j; k++) {
                    minValue = Math.min(minValue, dp[i - 1][k]);
                }

                dp[i][j] = minValue + addFatigueDegree;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = n; i <= m; i++) {
            result = Math.min(result, dp[n][i]);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}