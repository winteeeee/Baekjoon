package silver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem_24484 {
    static boolean[] visited;
    static ArrayList<Integer>[] edges;
    static int[] d, t;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        t = new int[n + 1];
        d = new int[n + 1];
        visited = new boolean[n + 1];
        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            d[i] = -1;
        for (int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges[a].add(b);
            edges[b].add(a);
        }
        for (int i = 1; i <= n; i++)
            Collections.sort(edges[i], Collections.reverseOrder());

        dfs(r, 0);

        bw.write(String.valueOf(getResult(n)));
        bw.flush();
        bw.close();
    }

    static void dfs(int vertex, int dCount) {
        visited[vertex] = true;
        d[vertex] = dCount;
        t[vertex] = count++;

        for (int nextVertex : edges[vertex]) {
            if (!visited[nextVertex]) {
                dfs(nextVertex, dCount + 1);
            }
        }
    }

    static long getResult(int n) {
        long result = 0;
        for (int i = 1; i <= n; i++)
            result += (long)d[i] * (long)t[i];
        return result;
    }
}
