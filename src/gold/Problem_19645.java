package gold;

import java.io.*;
import java.util.*;

public class Problem_19645 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] hamburgers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sum = Arrays.stream(hamburgers).sum();
        boolean[][] dp = new boolean[sum + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
                for (int k = sum; k >= 0; k--) {
                    if (j >= hamburgers[i])
                        dp[j][k] |= dp[j - hamburgers[i]][k];
                    if (k >= hamburgers[i])
                        dp[j][k] |= dp[j][k - hamburgers[i]];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            for (int j = 0; j <= i; j++) {
                int c = sum - i - j;
                if (dp[i][j] && i >= c && j >= c) {
                    ans = Math.max(ans, c);
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}
