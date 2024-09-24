package gold;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
[문제]
다음은 "Impractically Complicated Products Corporation"
공장의 제조 라인과 이에 대응하는 저장소에 관한 문제입니다.
공장에는 여러 개의 제조 라인이 있으며, 동일한 개수의 저장소가 있습니다.
이 저장소로 물품을 옮기기 위해 각각의 제조 라인과
대응하는 저장소 사이에 평행한 컨베이어 벨트가 설치되어 있습니다.

이제 여러 개의 로봇 팔을 설치하여
인접한 컨베이어 벨트 간에 물품을 옮길 수 있도록 하려고 합니다.
즉, 로봇 팔을 사용하면 한 컨베이어 벨트에서 물품을 들어 올려
옆의 컨베이어 벨트에 놓을 수 있습니다.
이를 통해 서로 다른 제조 라인의 물품을 여러 저장소로 혼합하여 보낼 수 있습니다.

로봇 팔의 위치에 따라, 각 제조 라인의 물품은 특정 저장소로만 전달될 수 있습니다.
주어진 컨베이어 벨트의 개수와 로봇 팔의 위치에 따라, 각 저장소로
몇 개의 제조 라인에서 물품을 전달할 수 있는지 찾아야 합니다.

[입력]
입력은 단일 테스트 케이스로 구성되어 있습니다.

n m
x1 y1
.
.
.
xm ym

첫 번째 줄에는 정수 n과 m이 주어집니다. 정수 n (2 ≤ n ≤ 200000)은 컨베이어 벨트의 개수이며,
컨베이어 벨트는 1부터 n까지 번호가 매겨져 있습니다. 번호 차이가 1인 컨베이어 벨트는 서로 인접해 있습니다.
모든 컨베이어 벨트는 x = 0에서 시작하여 x = 100000에서 끝납니다. 정수 m (1 ≤ m < 100000)은 로봇 팔의 개수입니다.

다음 m개의 줄에는 각 로봇 팔의 위치를 나타내는 두 정수 xi와 yi가 주어집니다.
여기서 xi는 i번째 로봇 팔의 x좌표(0 < xi < 100000)이며, yi (1 ≤ yi < n)는
로봇 팔이 컨베이어 벨트 yi 또는 yi+1에서 물품을 집어 x = xi에서 다른 컨베이어 벨트로 옮길 수 있음을 나타냅니다.

어떠한 두 로봇 팔도 동일한 x좌표를 가지지 않으며, xi ≠ xj (i ≠ j)임이 보장됩니다.

[출력]
한 줄에 n개의 정수를 출력하세요. 출력되는 i번째 정수는 컨베이어 벨트 i에 연결된 저장소로 물품을 전달할 수 있는 제조 라인의 개수입니다.
 */

public class Problem_13401 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);

        var robots = new Point[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            robots[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(robots, Comparator.comparingInt(o -> o.x));
        //x = robot.y의 값. y = robot.y + 1의 값
        var prevValues = new Point[n + 1];
        for (var robot : robots) {
            int y = dp[robot.y];
            int yPlusOne = dp[robot.y + 1];

            if (prevValues[robot.y] == null) {
                //로봇팔을 만나면 옆 라인의 값만큼 접근가능하므로 옆라인의 값만큼 더해줌
                dp[robot.y] += yPlusOne;
                dp[robot.y + 1] += y;
                prevValues[robot.y] = new Point(dp[robot.y], dp[robot.y + 1]);
            } else {
                //만일 해당 로봇팔의 좌표를 이미 계산한 적이 있다면
                //과거 값과 비교하여 차이값만을 더해주어 추가적인 라인에 얼만큼 접근 가능한지를 더해야함
                y = dp[robot.y] - prevValues[robot.y].x;
                yPlusOne = dp[robot.y + 1] - prevValues[robot.y].y;

                dp[robot.y] += yPlusOne;
                dp[robot.y + 1] += y;

                prevValues[robot.y].x = dp[robot.y];
                prevValues[robot.y].y = dp[robot.y + 1];
            }
        }

        var sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
            sb.append(dp[i]).append(' ');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
