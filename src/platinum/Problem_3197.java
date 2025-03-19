package platinum;

import java.io.*;
import java.util.*;

public class Problem_3197 {
    static int R, C;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[][] set;
    static char[][] map;
    static boolean[][] v;
    static int[] b1, b2;
    static Queue<int[]> cq = new ArrayDeque<>();
    static Queue<int[]> nq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        set = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'L') {
                    if (b1 == null) {
                        b1 = new int[2];
                        b1[0] = i; b1[1] = j;
                    } else {
                        b2 = new int[2];
                        b2[0] = i; b2[1] = j;
                    }
                }
            }
        }

        //분리집합 초기화
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                set[i][j] = i * C + j;
            }
        }

        //BFS를 수행하여
        //union해주는 동시에 cq를 채움
        v = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!v[i][j] && map[i][j] != 'X') {
                    bfs(i, j, cq);
                }
            }
        }

        //이후 시뮬을 돌리며 백조의 루트가 같은지 확인
        int res = 0;
        while (!cq.isEmpty()) {
            if (find(b1[0], b1[1]) == find(b2[0], b2[1])) {
                break;
            }

            //cq에 있는 얼음들을 전부 녹이고
            for (int[] target : cq) {
                map[target[0]][target[1]] = '.';
                //현재 위치를 다른 집합과 병합해주고
                for (int i = 0; i < dr.length; i++) {
                    int nr = target[0] + dr[i];
                    int nc = target[1] + dc[i];
                    if (bc(nr, nc) && map[nr][nc] != 'X' && v[nr][nc]) {
                        union(target[0], target[1], nr, nc);
                    }
                }
                //도달 가능하면서 방문하지 않은 X가 아닌 원소들 모두와 union
                //이때 nq도 같이 채움
                bfs(target[0], target[1], nq);
            }

            //cq와 nq를 교체
            cq = new ArrayDeque<>(nq);
            nq = new ArrayDeque<>();
            res++;
        }
        System.out.print(res);
    }

    static void bfs(int i, int j, Queue<int[]> xQ) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {i, j});
        v[i][j] = true;

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            union(i, j, cur[0], cur[1]);

            for (int k = 0; k < dr.length; k++) {
                int nr = cur[0] + dr[k];
                int nc = cur[1] + dc[k];

                if (bc(nr, nc)) {
                    if (v[nr][nc]) continue;
                    if (map[nr][nc] != 'X') {
                        q.add(new int[] {nr, nc});
                    } else {
                        xQ.add(new int[] {nr, nc});
                    }
                    v[nr][nc] = true;
                }
            }
        }
    }

    static int find(int r, int c) {
        if (r * C + c == set[r][c]) {
            return set[r][c];
        }

        return set[r][c] = find(set[r][c] / C, set[r][c] % C);
    }

    static void union(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2) return;
        int p1 = find(r1, c1);
        int p2 = find(r2, c2);

        set[p1 / C][p1 % C] = set[p2 / C][p2 % C];
    }

    static boolean bc(int r, int c) {
        return (0 <= r && r < R) && (0 <= c && c < C);
    }
}