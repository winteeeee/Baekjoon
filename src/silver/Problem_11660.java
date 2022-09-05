package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_11660 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());

                if(j == 0)
                    dp[i][j] = matrix[i][j];

                else {
                    dp[i][j] = dp[i][j - 1] + matrix[i][j];
                }
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            int result = 0;
            for(int j = x1; j <= x2; j++) {
                if (y1 - 1 >= 0)
                    result += dp[j][y2] - dp[j][y1 - 1];
                else
                    result += dp[j][y2];
            }

            bw.write(result +"\n");
        }

        bw.flush();
        bw.close();
    }
}
