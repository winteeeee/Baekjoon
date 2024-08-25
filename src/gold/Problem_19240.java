package gold;

import java.io.*;
import java.util.*;

public class Problem_19240 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        var sb = new StringBuilder();
        for (var i = 0; i < t; i++) {
            var st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(solve(br, n, m)).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static String solve(BufferedReader br, int n, int m) throws IOException {
        ArrayList<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            var st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            edges[x].add(y);
            edges[y].add(x);
        }

        boolean[] visited = new boolean[n + 1];
        boolean[] isRed = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                q.add(i);
                visited[i] = true;
                isRed[i] = true;

                while (!q.isEmpty()) {
                    int vertex = q.remove();

                    for (int nextVertex : edges[vertex]) {
                        if (!visited[nextVertex]) {
                            visited[nextVertex] = true;
                            isRed[nextVertex] = !isRed[vertex];
                            q.add(nextVertex);
                        } else {
                            if (isRed[vertex] == isRed[nextVertex]) {
                                return "NO";
                            }
                        }
                    }
                }
            }
        }

        return "YES";
    }
}
