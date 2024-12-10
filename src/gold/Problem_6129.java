package gold;

import java.io.*;
import java.util.*;

public class Problem_6129 {
    static class Cow implements Comparable<Cow> {
        int r;
        int c;
        int direction;
        int cnt;

        public Cow(int r, int c, int direction, int cnt) {
            this.r = r;
            this.c = c;
            this.direction = direction;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Cow cow) {
            int comp1 = Integer.compare(r, cow.r);
            int comp2 = Integer.compare(c, cow.c);
            int comp3 = Integer.compare(direction, cow.direction);

            if (comp1 != 0)
                return comp1;
            if (comp2 != 0)
                return comp2;
            return comp3;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        char[][] map = new char[n][n];
        var visited = new TreeMap<Cow, Integer>();
        for (int i = 0; i < n; i++)
            map[i] = br.readLine().toCharArray();

        int startR = 0;
        int startC = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'A') {
                    startR = i;
                    startC = j;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        var q = new LinkedList<Cow>();
        for (int i = 0; i < 4; i++) {
            int nextR = startR + dr[i];
            int nextC = startC + dc[i];
            Cow nextCow = new Cow(nextR, nextC, i, 0);

            if (boundaryCheck(n, nextR, nextC) && map[nextR][nextC] != 'x') {
                q.add(nextCow);
                visited.put(nextCow, 0);
            }
        }

        while (!q.isEmpty()) {
            Cow cur = q.remove();
            if (map[cur.r][cur.c] == 'B') {
                ans = Math.min(ans, cur.cnt);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextR = cur.r + dr[i];
                int nextC = cur.c + dc[i];
                int nextCnt = getNextCnt(cur.direction, i, cur.cnt);
                Cow nextCow = new Cow(nextR, nextC, i, nextCnt);

                if (boundaryCheck(n, nextR, nextC) && map[nextR][nextC] != 'x') {
                    if (!visited.containsKey(nextCow) || visited.get(nextCow) > nextCnt) {
                        q.add(nextCow);
                        visited.put(nextCow, nextCnt);
                    }
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    public static boolean boundaryCheck(int n, int r, int c) {
        return (0 <= r && r < n) && (0 <= c && c < n);
    }

    public static int getNextCnt(int direction, int i, int cnt) {
        if ((direction == 0 || direction == 1) && (i == 2 || i == 3))
            return cnt + 1;
        else if ((direction == 2 || direction == 3) && (i == 0 || i == 1))
            return cnt + 1;
        else
            return cnt;
    }
}
