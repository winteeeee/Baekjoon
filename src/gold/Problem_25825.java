package gold;

import java.io.*;
import java.util.StringTokenizer;

/*
어느 한 사람을 선택해도 결국 처음엔 집단 내부의 인원에게 알려줘야 하므로
사람이 아닌 집단을 선택하는 문제. 모든 집단을 선택하는 경우의 수를 따져보면 된다.
사람의 경우 12!으로 시간초과가 나지만 집단을 선택한다고 생각했을 땐 6!으로
충분한 시간 내에 답을 구할 수 있다.

A, B가 속한 특정 그룹이 있다고 하자. A에게 메세지가 전달되었을 때 해당 메시지를 다른 그룹에게 전달하는 주체는
A가 아닌 B임에 유의하자.
 */

public class Problem_25825 {
    static int result = Integer.MAX_VALUE;
    static int[][] transportTime = new int[13][13];
    static boolean[] visited = new boolean[6];

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int from = 1; from <= 12; from++) {
            var st = new StringTokenizer(br.readLine());
            for (int to = 1; to <= 12; to++) {
                transportTime[from][to] = Integer.parseInt(st.nextToken());
            }
        }

        findResult(-1, 0, 0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void findResult(int from, int time, int depth) {
        if (from != -1) {
            int sameGroupFriend = from % 2 != 0 ? from + 1 : from - 1;
            time += transportTime[from][sameGroupFriend];
            from = sameGroupFriend;
        }

        if (depth == 6) {
            result = Math.min(result, time);
        }

        for (int i = 1; i <= 12; i++) {
            int groupNumber = (i - 1) / 2;

            if (!visited[groupNumber]) {
                int anotherGroupTransportTime = from == -1 ? 0 : transportTime[from][i];

                visited[groupNumber] = true;
                findResult(i, time + anotherGroupTransportTime, depth + 1);
                visited[groupNumber] = false;
            }
        }
    }
}
