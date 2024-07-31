package silver;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
BFS하여 다음칸에 양으로 도달할 수 있으면 현재칸을 울타리로 막고
현재 칸을 울타리로 못막는 상황이 발생하면 0을 출력한다.
(결국 양 주변을 울타리로 감싸는 형태가 됨)

이렇게만 보면 단순한 그래프 탐색 문제 같아 보이지만..
최소로 울타리를 설치하는 문제가 아니므로
늑대와 양이 붙어있다면 0을 출력하고 아니라면 모든 .을 D로 바꾸면 된다.

이 경우 수행시간도 매우 짧게 나오는 듯
 */

public class Problem_16956 {
    static int r;
    static int c;
    static char[][] map;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static class 좌표 {
        int r;
        int c;

        좌표(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        입력();
        풀이();
    }

    public static void 입력() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String status = br.readLine();
            for (int j = 0; j < status.length(); j++) {
                map[i][j] = status.charAt(j);
            }
        }
    }

    public static void 풀이() throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'W') {
                    좌표 늑대 = new 좌표(i, j);
                    try {
                        너비우선탐색(늑대);
                    } catch (Exception e) {
                        bw.write(e.getMessage());
                        bw.flush();
                        bw.close();

                        return;
                    }
                }
            }
        }

        bw.write("1\n");
        map출력(bw);
        bw.flush();
        bw.close();
    }

    public static void 너비우선탐색(좌표 늑대) throws Exception {
        Queue<좌표> q = new LinkedList<>();
        boolean[][] 방문함 = new boolean[r][c];
        방문함[늑대.r][늑대.c] = true;
        q.add(늑대);

        while (!q.isEmpty()) {
            좌표 현재늑대 = q.remove();

            for (int i = 0; i < 4; i++) {
                int 이동할행 = 현재늑대.r + dr[i];
                int 이동할열 = 현재늑대.c + dc[i];

                if (범위내인가(이동할행, 이동할열) && !방문함[이동할행][이동할열]) {
                    if (map[이동할행][이동할열] == '.') {
                        방문함[이동할행][이동할열] = true;
                        q.add(new 좌표(이동할행, 이동할열));
                    } else if (map[이동할행][이동할열] == 'S') {
                        if (map[현재늑대.r][현재늑대.c] == '.' || map[현재늑대.r][현재늑대.c] == 'D') {
                            map[현재늑대.r][현재늑대.c] = 'D';
                        } else {
                            throw new Exception("0");
                        }
                    }
                }
            }
        }
    }

    public static boolean 범위내인가(int 열, int 행) {
        return (0 <= 열 && 열 < r) && (0 <= 행 && 행 < c);
    }

    public static void map출력(BufferedWriter bw) throws IOException {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                bw.write(map[i][j]);
            }
            bw.write('\n');
        }
    }
}
