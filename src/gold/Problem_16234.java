package gold;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Problem_16234 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] map = br.lines()
                .limit(n)
                .map(line ->
                        Arrays.stream(line.split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray())
                .toArray(int[][]::new);

        int ans = 0;
        while (true) {
            var alliances = new TreeMap<Integer, ArrayList<Point>>();
            boolean[][] visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        findAlliance(i, j, r, l, alliances.size(), map, visited, alliances);
                    }
                }
            }

            if (alliances.isEmpty())
                break;

            for (Map.Entry<Integer, ArrayList<Point>> e : alliances.entrySet()) {
                var countries = e.getValue();
                int sum = countries.stream().mapToInt(p -> map[p.x][p.y]).sum();
                for (Point p : countries) {
                    map[p.x][p.y] = sum / countries.size();
                }
            }

            ans++;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    public static void findAlliance(int i, int j, int r, int l, int idx,
                                    int[][] map,
                                    boolean[][] visited,
                                    TreeMap<Integer, ArrayList<Point>> a) {
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        int n = map.length;

        var q = new LinkedList<Point>();
        var initPoint = new Point(i, j);
        q.add(initPoint);
        visited[i][j] = true;

        while (!q.isEmpty()) {
            var cur = q.remove();
            for (int k = 0; k < 4; k++) {
                int nextR = cur.x + dr[k];
                int nextC = cur.y + dc[k];
                var nextPoint = new Point(nextR, nextC);

                if (boundaryCheck(nextR, nextC, n) && !visited[nextR][nextC] && moveable(cur, nextPoint, r, l, map)) {
                    var list = a.getOrDefault(idx, new ArrayList<>());
                    list.add(nextPoint);
                    a.put(idx, list);

                    q.add(nextPoint);
                    visited[nextR][nextC] = true;
                }
            }
        }

        if (a.get(idx) != null && !a.get(idx).isEmpty()) {
            var list = a.get(idx);
            list.add(initPoint);
            a.put(idx, list);
        }
    }

    public static boolean boundaryCheck(int r, int c, int n) {
        return (0 <= r && r < n) && (0 <= c && c < n);
    }

    public static boolean moveable(Point p1, Point p2, int r, int l, int[][] map) {
        int diff = Math.abs(map[p1.x][p1.y] - map[p2.x][p2.y]);
        return (l <= diff && diff <= r);
    }
}
