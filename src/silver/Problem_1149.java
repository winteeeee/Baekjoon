package silver;

import java.io.*;
import java.util.*;

public class Problem_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int RED = 0;
        final int GREEN = 1;
        final int BLUE = 2;
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][3];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            try {
                dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + r;
                dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + g;
                dp[i][BLUE] = Math.min(dp[i - 1][RED], dp[i - 1][GREEN]) + b;
            } catch (ArrayIndexOutOfBoundsException e) {
                dp[i][RED] = r;
                dp[i][GREEN] = g;
                dp[i][BLUE] = b;
            }
        }

        int[] temp = new int[3];
        temp[0] = dp[n - 1][RED];
        temp[1] = dp[n - 1][GREEN];
        temp[2] = dp[n - 1][BLUE];
        Arrays.sort(temp);

        bw.write(String.valueOf(temp[0]));
        bw.flush();
        bw.close();
    }
}
