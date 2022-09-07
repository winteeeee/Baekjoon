package gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_11054 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n + 1];
        int[] dp = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if(sequence[i] > sequence[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int result = 0;
        for(int i = 1; i <= n; i++) {
            int[] dp2 = new int[n + 1];
            int max = 0;
            for(int j = i + 1; j <= n; j++) {
                for(int k = 0; k < j; k++) {
                    if (sequence[j] < sequence[k]) {
                        dp2[j] = Math.max(dp2[k] + 1, dp2[j]);

                        if (dp2[j] > max)
                            max = dp2[j];
                    }
                }
            }

            if (dp[i] + max > result) {
                result = dp[i] + max;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}

