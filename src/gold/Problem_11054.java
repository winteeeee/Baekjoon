package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_11054 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n + 1];
        int[] dp = new int[n + 1];
        int[] dp2 = new int[n + 1];

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

        for(int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if(sequence[i] < sequence[j])
                    dp2[i] = Math.max(dp2[j] + 1, dp2[i]);
            }
        }

        int count = 0;
        for(int i = 1; i <= n; i++) {
            if(count < dp[i] + dp2[i])
                count = dp[i] + dp2[i];
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }
}

