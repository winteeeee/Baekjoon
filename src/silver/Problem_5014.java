package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
웰노운 BFS 문제
선형 공간에서 BFS를 수행하며 목표 위치에 도달했는지, 도달했다면 최소 횟수가 몇번인지를 구한다.
 */

public class Problem_5014 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int result = Integer.MAX_VALUE;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[f + 1];
        q.add(s); q.add(0);
        visited[s] = true;

        while (!q.isEmpty()) {
            int cur = q.remove();
            int buttonCnt = q.remove();
            if (cur == g) {
                result = Math.min(result, buttonCnt);
            } else {
                if (cur + u <= f && !visited[cur + u]) {
                    q.add(cur + u);
                    q.add(buttonCnt + 1);
                    visited[cur + u] = true;
                }

                if (cur - d > 0 && !visited[cur - d]) {
                    q.add(cur - d);
                    q.add(buttonCnt + 1);
                    visited[cur - d] = true;
                }
            }
        }

        if (result == Integer.MAX_VALUE)
            bw.write("use the stairs");
        else
            bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
