package gold;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Problem_25417 {
    static final int SIZE = 5;
    static int[][] map = new int[SIZE][SIZE];
    static boolean[][] visited = new boolean[SIZE][SIZE];

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        for (int i = 0; i < SIZE; i++)
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int ans = -1;
        var q = new LinkedList<int[]>();
        q.add(new int[]{pos[0], pos[1], 0});
        visited[pos[0]][pos[1]] = true;
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            if (map[cur[0]][cur[1]] == 1) {
                ans = cur[2];
                break;
            }

            for (int i = 0; i < 4; i++) {
                //걸어가는 경우
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];
                if (boundaryCheck(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] != -1) {
                    q.add(new int[]{nextR, nextC, cur[2] + 1});
                    visited[nextR][nextC] = true;
                }

                //뛰어가는 경우
                int[] runningPos = getRunningPos(cur, i, cur[2]);
                if (!visited[runningPos[0]][runningPos[1]]) {
                    q.add(runningPos);
                    visited[runningPos[0]][runningPos[1]] = true;
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    public static boolean boundaryCheck(int r, int c) {
        return (0 <= r && r < SIZE) && (0 <= c && c < SIZE);
    }

    public static int[] getRunningPos(int[] cur, int direction, int cnt) {
        //0: 상, 1: 우, 2: 하, 3: 좌
        int r;
        int c;
        if (direction == 0) {
            r = 0;
            c = cur[1];
            for (int i = cur[0] - 1; i >= 0; i--) {
                if (map[i][c] == -1) {
                    r = i + 1;
                    break;
                } else if (map[i][c] == 7) {
                    r = i;
                    break;
                }
            }
        } else if (direction == 1) {
            r = cur[0];
            c = SIZE - 1;
            for (int i = cur[1] + 1; i < SIZE; i++) {
                if (map[r][i] == -1) {
                    c = i - 1;
                    break;
                } else if (map[r][i] == 7) {
                    c = i;
                    break;
                }
            }
        } else if (direction == 2) {
            r = SIZE - 1;
            c = cur[1];
            for (int i = cur[0] + 1; i < SIZE; i++) {
                if (map[i][c] == -1) {
                    r = i - 1;
                    break;
                } else if (map[i][c] == 7) {
                    r = i;
                    break;
                }
            }
        } else {
            r = cur[0];
            c = 0;
            for (int i = cur[1] - 1; i >= 0; i--) {
                if (map[r][i] == -1) {
                    c = i + 1;
                    break;
                } else if (map[r][i] == 7) {
                    c = i;
                    break;
                }
            }
        }

        return new int[]{r, c, cnt + 1};
    }
}
