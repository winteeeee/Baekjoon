package silver;

import java.util.*;
import java.io.*;

public class Problem_17626 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[50001];
        Arrays.fill(dp, 5);
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int a = 4; a < 50001; a++) {
            if(Math.sqrt(a) - (int)Math.sqrt(a) == 0) {
                dp[a] = 1;
                dp[a+1] = 2;
                dp[a+2] = 3;
            }

            for(int b = 1; b <= (int)Math.sqrt(a); b++) {
                int i = dp[a - b * b] + dp[b * b];
                if(dp[a] > i)
                    dp[a] = i;
            }
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
        bw.close();
    }
}
