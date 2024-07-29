package gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
너무 고생했던 문제
처음엔 문제 조건을 잘못 이해하고 브루트포스로 생각하면서 풀었으나 이후 문제 조건을 제대로 이해하고
그리디로 접근하여 풀었으나 틀려 태그를 까보니까 DP여서 이후로는 DP로 생각하면서 풀었다.

문제의 조건을 간단히하면 결국 비용이 최대가 되도록 대각선으로 건물을 지어라인데
따라서 먼저 건물을 x축을 기준으로 정렬해준 후
대각선의 조건이 만족되면 DP의 값을 갱신한다.

DP식의 의미는
DP[i][0] = i번째 건물을 '반드시'포함하며 좌하단 ~ 우상단 대각선을 그을 때 비용의 최댓값이다.
DP[i][1]은 조건을 동일하되 대각선의 방향이 반대이다.

일반적인 DP식과 다르게 N번째 테이블에 도달할 때 비용의 최댓값이 들어있지 않다.
DP식의 의미 자체가 i번째 건물을 반드시 포함하였을 경우의 비용인데 실제 최적해는 N번째 건물이 포함되지 않을 수도 있기 때문이다.
이걸 인지하지 못해서 굉장히 애를 먹었다. 새로운 사실을 배운 문제.
DP 문제라고 해서 N번째 테이블에 답이 없을 수도 있다.
 */

public class Problem_2296 {
    static class Building implements Comparable<Building> {
        int x;
        int y;
        int c;

        public Building(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Building o) {
            return Integer.compare(x, o.x);
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Building[] buildings = new Building[n];
        for (int i = 0; i < n; i++) {
            var st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            buildings[i] = new Building(x, y, c);
        }

        Arrays.sort(buildings);
        int[][] dp = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = buildings[i - 1].c;
            dp[i][1] = buildings[i - 1].c;
        }

        int result = buildings[0].c;
        for (int i = 2; i <= n; i++) {
            Building cur = buildings[i - 1];

            for (int j = 1; j < i; j++) {
                Building prev = buildings[j - 1];
                dp[i][0] = prev.y < cur.y ? Math.max(dp[j][0] + cur.c, dp[i][0]) : dp[i][0];
                dp[i][1] = prev.y > cur.y ? Math.max(dp[j][1] + cur.c, dp[i][1]) : dp[i][1];
            }

            result = Math.max(result, Math.max(dp[i][0], dp[i][1]));
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
