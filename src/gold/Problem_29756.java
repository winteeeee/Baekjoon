package gold;

import java.io.*;
import java.util.StringTokenizer;

/*
문제가 대놓고 배낭 문제이다. 다만 일반적인 배낭 문제는 아니고 회복이라는 기믹이 적절히 추가되었다.
따라서 i번째 물건을 선택할 때 0번째 ~ i - 1번째의 테이블을 모두 참고할 필요가 있다.
일반적인 배낭 문제의 경우 i - 1번째 인덱스에 최댓값이 들어있지만
이 경우 회복 때문에 i - 1번째에서는 선택될 수 없었던 선택지가 추가될 수 있기 때문

시간초과에 걸리려나? 싶었는데 매우 빠르게 통과하는 것을 확인할 수 있었다.
 */

public class Problem_29756 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] s = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        int[] h = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        int[][] dp = new int[n + 1][101];
        for (int i = 1; i <= n; i++) {
            int curS = s[i];
            int curH = h[i];

            for (int j = 0; j <= 100; j++) {
                if (j - curH >= 0) {
                    for (int l = 1; l <= i; l++) {
                        int nextJ = Math.min(j - curH + (k * l), 100);
                        dp[i][j] = Math.max(dp[i - l][nextJ] + curS, dp[i][j]);
                    }
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                }

                result = Math.max(result, dp[i][j]);
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
