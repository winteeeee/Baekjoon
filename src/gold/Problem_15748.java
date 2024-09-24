package gold;

import java.awt.*;
import java.io.*;
import java.util.*;

/*
[문제]
다음은 "Farmer John"과 그의 개인 트레이너 Bessie의 등산에 관한 문제입니다.
이 문제에서 산은 길이 L 미터인 직선 경로로 표현됩니다(1 <= L <= 10^6).
Farmer John은 경로를 쉬지 않고 일정한 속도 r_f 초/미터(1 <= r_f <= 10^6)로 등산합니다.

반면에, Bessie는 쉬면서 갈 수 있지만, 아무 곳에서나 쉴 수는 없습니다!
경로에는 N개의 휴식 장소가 있으며(1 <= N <= 10^5), i번째 휴식 장소는 출발점에서 x_i 미터 떨어져 있고(0 < x_i < L),
그곳에는 맛있는 풀이 있어 c_i의 맛있음 값을 가집니다(1 <= c_i <= 10^6).
Bessie가 i번째 휴식 장소에서 t 초 동안 쉰다면, 그녀는 c_i * t 만큼의 맛있음 단위를 얻습니다.

휴식 장소에 있지 않을 때, Bessie는 고정된 속도 r_b 초/미터(1 <= r_b <= 10^6)로 산을 등산합니다.
Bessie는 젊고 건강하기 때문에, r_b는 항상 r_f보다 빠릅니다.

Bessie는 맛있는 풀을 최대한 많이 먹고 싶어 하지만, Farmer John에 대해 걱정하고 있습니다.
만약 등산 도중 어느 시점에서라도 Bessie가 Farmer John보다 경로에서 뒤처지면, Farmer John은 동기를 잃을 수 있다고 생각합니다!

Bessie가 Farmer John이 산을 다 오르는 동안 뒤처지지 않으면서 얻을 수 있는 최대 맛있음 단위를 찾아주세요.


[입력]
첫 번째 줄에는 네 개의 정수 L, N, r_f, r_b가 주어집니다.
그 다음 N개의 줄에는 각 휴식 장소에 대한 정보가 주어집니다. i번째 줄은 i번째 휴식 장소가 출발점에서 x_i 미터 떨어져 있으며,
그곳의 풀이 c_i의 맛있음 값을 가진다는 두 개의 정수 x_i, c_i로 구성됩니다.

r_f > r_b임이 보장되며, 0 < x_1 < ... < x_n < L이 성립합니다. r_f와 r_b는 초/미터 단위로 주어집니다!

[출력]
Bessie가 얻을 수 있는 최대 맛있음 단위를 나타내는 정수 하나를 출력하세요.

[힌트]
이 예시에서는 Bessie가 x = 7 휴식 장소에서 7초 동안 쉬는 것이 최적이며(14의 맛있음 단위 획득),
x = 8 휴식 장소에서 추가로 1초 동안 쉬는 것이 최적입니다(1의 맛있음 단위 획득, 총 15 맛있음 단위).
 */

public class Problem_15748 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        long rf = Long.parseLong(st.nextToken());
        long rb = Long.parseLong(st.nextToken());

        Point[] restStops = new Point[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            restStops[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        long result = 0;
        Arrays.sort(restStops, (o1, o2) -> Long.compare(o2.y, o1.y));
        var maxRestStops = new ArrayList<Point>();
        for (Point restStop : restStops) {
            if (maxRestStops.isEmpty()) {
                maxRestStops.add(restStop);
            } else {
                if (restStop.x > maxRestStops.get(maxRestStops.size() - 1).x) {
                    maxRestStops.add(restStop);
                }
            }
        }

        long johnPos = 0;
        long bessiePos = 0;
        for (Point cur : maxRestStops) {
            long newBessiePos = rb * cur.x;
            long db = newBessiePos - bessiePos;
            johnPos += db;

            result += (rf * cur.x - johnPos) * cur.y;
            bessiePos = newBessiePos;
            johnPos = rf * cur.x;
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
