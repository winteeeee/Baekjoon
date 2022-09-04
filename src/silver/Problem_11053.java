package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_11053 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n + 1];
        int[] dp = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());


        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if(sequence[i] > sequence[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);

                    if(dp[i] > max)
                        max = dp[i];
                }
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}
