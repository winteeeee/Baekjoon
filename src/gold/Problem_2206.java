package gold;

import java.io.*;
import java.util.*;

public class Problem_2206 {
    static int result = -1;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][m + 1];
        boolean[][][] visited = new boolean[n + 1][m + 1][2];


        for(int i = 1; i <= n; i++) {
            String temp = br.readLine();
            for(int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j - 1)));
            }
        }

        bfs(1, 1, map, visited);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void bfs(int startX, int startY, int[][] map, boolean[][][] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(startX);
        q.add(startY);
        q.add(1);
        q.add(0);
        visited[1][1][1] = true;
        int[] coordinateX = {0, 0, 1, -1};
        int[] coordinateY = {1, -1, 0, 0};

        while(!q.isEmpty()) {
            int x = q.remove();
            int y = q.remove();
            int distance = q.remove();
            int count = q.remove();
            if(x == m && y == n) {
                result = distance;
                break;
            }

            for(int i = 0; i < 4; i++) {
                if((x + coordinateX[i] > 0 && x + coordinateX[i] <= m) && (y + coordinateY[i] > 0 && y + coordinateY[i] <= n)) {
                    int cur = map[y + coordinateY[i]][x + coordinateX[i]];
                    if ((cur == 0 && count == 0) && !visited[y + coordinateY[i]][x + coordinateX[i]][0]) {
                        q.add(x + coordinateX[i]);
                        q.add(y + coordinateY[i]);
                        q.add(distance + 1);
                        q.add(count);
                        visited[y + coordinateY[i]][x + coordinateX[i]][0] = true;
                    }

                    else if((cur == 0 && count == 1) && !visited[y + coordinateY[i]][x + coordinateX[i]][1]) {
                        q.add(x + coordinateX[i]);
                        q.add(y + coordinateY[i]);
                        q.add(distance + 1);
                        q.add(count);
                        visited[y + coordinateY[i]][x + coordinateX[i]][1] = true;
                    }

                    else if ((cur == 1 && count == 0) && !visited[y + coordinateY[i]][x + coordinateX[i]][1]) {
                        q.add(x + coordinateX[i]);
                        q.add(y + coordinateY[i]);
                        q.add(distance + 1);
                        q.add(count + 1);
                        visited[y + coordinateY[i]][x + coordinateX[i]][1] = true;
                    }
                }
            }
        }
    }
}
