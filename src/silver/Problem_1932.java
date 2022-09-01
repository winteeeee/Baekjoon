package silver;

import java.io.*;
import java.util.*;

public class Problem_1932 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] triangle = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            triangle[i] = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < i; j++) {
                triangle[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i < n + 1; i++) {
            if(i == 1)
                dp[1][1] = triangle[1].get(0);

            else if(i == 2) {
                dp[2][1] = dp[1][1] + triangle[2].get(0);
                dp[2][2] = dp[1][1] + triangle[2].get(1);
            }

            else {
                for(int j = 1; j <= i; j++) {
                    if(j == 1) {
                        dp[i][j] = triangle[i].get(0) + dp[i - 1][1];
                    }

                    else if(j == i) {
                        dp[i][j] = triangle[i].get(i - 1) + dp[i - 1][i - 1];
                    }

                    else {
                        dp[i][j] = triangle[i].get(j - 1) + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                    }
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            if(result < dp[n][i])
                result = dp[n][i];
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
