package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
구현/시뮬레이션 문제라서 문제에 제시된대로 구현만 해주면 된다.
특정 경우의 수마다 맵이 달라지므로 시뮬레이션 전 가능한 맵을 모두 생성한 후
인덱스만 저장하는 방식으로 메모리를 아꼈다.

가만히 있는 선택지도 존재하므로 큐에서 꺼냈을 때 현재 위치에 해당하는 방문 배열을
false로 바꿀 필요가 있다.

맵 생성 로직에서 실수가 있어 살짝 시간이 걸렸다.
 */

public class Problem_16954 {
    static class Status {
        int r, c;
        int mapIdx;

        Status(int r, int c, int m) {
            this.r = r;
            this.c = c;
            mapIdx = m;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean isOne = false;
        int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] dc = {1, 0, -1, 1, 0, -1, 1, 0, -1};
        ArrayList<char[][]> mapList = new ArrayList<>();
        char[][] map = new char[8][8];

        for (int i = 0; i < 8; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        mapList.add(map);
        for (int i = 1; i <= 8; i++) {
            mapList.add(makeNextMap(mapList.get(i - 1)));
        }

        Queue<Status> q = new LinkedList<>();
        boolean[][] visited = new boolean[8][8];
        q.add(new Status(7, 0, 0));
        visited[7][0] = true;

        while (!q.isEmpty()) {
            Status cur = q.remove();
            char[][] curMap = mapList.get(cur.mapIdx);
            visited[cur.r][cur.c] = false;

            //목표 위치에 도달 시 종료
            if (cur.r == 0 && cur.c == 7) {
                isOne = true;
                break;
            }

            //캐릭터의 현재 위치가 벽이면 움직일 수 없음
            if (curMap[cur.r][cur.c] != '#') {
                for (int i = 0; i < 9; i++) {
                    int nextR = cur.r + dr[i];
                    int nextC = cur.c + dc[i];

                    if ((0 <= nextR && nextR < 8) && (0 <= nextC && nextC < 8)) {
                        if (!visited[nextR][nextC] && curMap[nextR][nextC] != '#') {
                            q.add(new Status(nextR, nextC, Math.min(cur.mapIdx + 1, 8)));
                            visited[nextR][nextC] = true;
                        }
                    }
                }
            }
        }

        if (isOne)
            bw.write("1");
        else
            bw.write("0");
        bw.flush();
        bw.close();
    }

    public static char[][] makeNextMap(char[][] map) {
        char[][] result = new char[8][8];
        ArrayList<Integer> rIdx = new ArrayList<>();
        ArrayList<Integer> cIdx = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                result[i][j] = map[i][j];

                if (result[i][j] == '#') {
                    rIdx.add(i);
                    cIdx.add(j);
                }
            }
        }


        for (int i = 0; i < rIdx.size(); i++) {
            int originalR = rIdx.get(i);
            int originalC = cIdx.get(i);

            if (result[originalR][originalC] == 'O') {
                result[originalR][originalC] = '#';
            } else {
                result[originalR][originalC] = '.';
            }

            if (originalR != 7) {
                if (result[originalR + 1][originalC] != '#') {
                    result[originalR + 1][originalC] = '#';
                } else {
                    result[originalR + 1][originalC] = 'O';
                }
            }
        }

        return result;
    }
}
