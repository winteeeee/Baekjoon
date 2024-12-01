package gold;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Problem_30367 {
    static class Jennifer implements Comparable<Jennifer> {
        int r;
        int c;
        int cnt;
        int direction;  //1: 위, 2: 오른, 3: 아래, 4: 왼

        public Jennifer(int r, int c, int cnt, int direction) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.direction = direction;
        }

        public Jennifer[] getNextPos() {
            Jennifer[] next = new Jennifer[4];
            if (direction != 4)
                next[0] = new Jennifer(r - 1, c, cnt + 1, 1);
            if (direction != 1)
                next[1] = new Jennifer(r, c + 1, cnt + 1, 2);
            if (direction != 2)
                next[2] = new Jennifer(r + 1, c, cnt + 1, 3);
            if (direction != 3)
                next[3] = new Jennifer(r, c - 1, cnt + 1, 4);
            return next;
        }

        public boolean boundaryCheck(int n, int m) {
            return (0 <= r && r < n) && (0 <= c && c < m);
        }

        @Override
        public int compareTo(Jennifer jennifer) {
            int comp1 = Integer.compare(r, jennifer.r);
            int comp2 = Integer.compare(c, jennifer.c);
            int comp3 = Integer.compare(direction, jennifer.direction);

            if (comp1 != 0) {
                return comp1;
            } else if (comp2 != 0) {
                return comp2;
            } else {
                return comp3;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        var visited = new TreeSet<Jennifer>();
        Point startPos = null;
        for (int i = 0; i < n; i++) {
            String partOfMap = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = partOfMap.charAt(j);
                if (map[i][j] == 'S')
                    startPos = new Point(i, j);
            }
        }

        int ans = -1;
        var q = new LinkedList<Jennifer>();
        Jennifer start = new Jennifer(startPos.x, startPos.y, 0, 0);
        q.add(start);
        visited.add(start);
        while (!q.isEmpty()) {
            Jennifer cur = q.remove();
            if (map[cur.r][cur.c] == 'E') {
                ans = cur.cnt;
                break;
            }

            Jennifer[] next = cur.getNextPos();
            for (Jennifer j : next) {
                if (j != null && j.boundaryCheck(n, m) && map[j.r][j.c] != '#' && !visited.contains(j)) {
                    visited.add(j);
                    q.add(j);
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}
