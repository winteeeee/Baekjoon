package silver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Problem_31871 {
    static int n, m;
    static int result = -1;
    static HashMap<Integer, Integer>[] edge;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        edge = new HashMap[n + 1];
        for (int i = 0; i <= n; i++)
            edge[i] = new HashMap<>();

        for (int i = 0; i < m; i++) {
            var st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            if (!edge[a].containsKey(b) || edge[a].get(b) < value)
                edge[a].put(b, value);
        }

        visited[0] = true;
        findResult(0, 0, 0);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    static void findResult(int vertex, int visitedCount, int time) {
        if (visitedCount == n) {
            int toZero = edge[vertex].getOrDefault(0, 0);
            if (toZero != 0)
                result = Math.max(time + toZero, result);
        }

        for (Map.Entry<Integer, Integer> entry : edge[vertex].entrySet()) {
            var to = entry.getKey();
            var value = entry.getValue();

            if (!visited[to]) {
                visited[to] = true;
                findResult(to, visitedCount + 1, time + value);
                visited[to] = false;
            }
        }
    }
}
