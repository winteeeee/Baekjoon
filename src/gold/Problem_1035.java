package gold;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Problem_1035 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    static class Board {
        int count;
        char[][] map;

        public Board(int count, char[][] map) {
            this.count = count;
            this.map = map;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Board board = (Board) o;
            return Objects.deepEquals(map, board.map);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(map);
        }

        public boolean check() {
            boolean[][] visited = new boolean[5][5];
            boolean bfsExecuted = false;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!visited[i][j] && map[i][j] == '*') {
                        if (bfsExecuted)
                            return false;

                        bfs(i, j, visited);
                        bfsExecuted = true;
                    }
                }
            }

            return true;
        }

        private void bfs(int r, int c, boolean[][] visited) {
            Queue<Point> q = new LinkedList<>();
            q.add(new Point(r, c));
            visited[r][c] = true;

            while (!q.isEmpty()) {
                var cur = q.remove();

                for (int i = 0; i < 4; i++) {
                    int nextR = cur.x + dr[i];
                    int nextC = cur.y + dc[i];

                    if (boundaryCheck(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == '*') {
                        q.add(new Point(nextR, nextC));
                        visited[nextR][nextC] = true;
                    }
                }
            }
        }

        public ArrayList<Board> getNextBoards() {
            //현재 맵을 기반으로 다음 보드들을 생성해서 반환
            var result = new ArrayList<Board>();
            var stars = new ArrayList<Point>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (map[i][j] == '*') {
                        stars.add(new Point(i, j));
                    }
                }
            }

            for (Point star : stars) {
                for (int i = 0; i < 4; i++) {
                    int nextR = star.x + dr[i];
                    int nextC = star.y + dc[i];

                    if (boundaryCheck(nextR, nextC) && map[nextR][nextC] == '.') {
                        char[][] newMap = mapDeepClone();
                        newMap[star.x][star.y] = '.';
                        newMap[nextR][nextC] = '*';
                        result.add(new Board(count + 1, newMap));
                    }
                }
            }

            return result;
        }

        private boolean boundaryCheck(int r, int c) {
            return (0 <= r && r < 5) && (0 <= c && c < 5);
        }

        private char[][] mapDeepClone() {
            char[][] newMap = new char[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++ ) {
                    newMap[i][j] = map[i][j];
                }
            }

            return newMap;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var map = br.lines()
                .limit(5)
                .map(String::toCharArray)
                .toArray(char[][]::new);
        Queue<Board> q = new LinkedList<>();
        var visited = new HashSet<Board>();

        var initBoard = new Board(0, map.clone());
        q.add(initBoard);
        visited.add(initBoard);

        int ans = -1;
        while (!q.isEmpty()) {
            var cur = q.remove();
            if (cur.check()) {
                ans = cur.count;
                break;
            }

            var nextBoards = cur.getNextBoards();
            for (Board b : nextBoards) {
                if (!visited.contains(b)) {
                    q.add(b);
                    visited.add(b);
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}
