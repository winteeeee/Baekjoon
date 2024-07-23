package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
그리디인가 싶지만 비용을 배낭의 무게로, 메모리 바이트 수를 가치로 생각하면
완벽하게 배낭 문제임을 알 수 있다.

따라서 배낭 문제 풀듯이 풀어주면 된다.
 */

public class Problem_7579 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static ArrayList<Integer> memory = new ArrayList<>();
    static ArrayList<Integer> cost = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        var st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost.add(Integer.parseInt(st.nextToken()));
        }

        int[][] dp = new int[n + 1][100 * n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 100 * n; j++) {
                if (j - cost.get(i - 1) >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost.get(i - 1)] + memory.get(i - 1));
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int i = 0; i <= 100 * n; i++) {
            if (dp[n][i] >= m) {
                bw.write(String.valueOf(i));
                break;
            }
        }
        bw.flush();
        bw.close();
    }
}
