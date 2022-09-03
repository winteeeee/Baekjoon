package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_2638 {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] cheese = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++)
                cheese[i][j] = Integer.parseInt(st.nextToken());
        }

        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};

        while(true) {
            boolean existOne = false;
            ArrayList<Integer> positionX = new ArrayList<>();
            ArrayList<Integer> positionY = new ArrayList<>();
            boolean[][] visited = new boolean[n][m];
            bfs(0, 0, n, m, cheese, visited);

            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if(cheese[i][j] == 1)
                        existOne = true;

                    int meltCount = 0;
                    for (int k = 0; k < 4; k++) {
                        if (cheese[i + y[k]][j + x[k]] == 0 && visited[i + y[k]][j + x[k]]) {
                            meltCount++;
                        }
                    }

                    if(meltCount >= 2) {
                        positionX.add(j);
                        positionY.add(i);
                    }
                }
            }

            for(int i = 0; i < positionX.size(); i++)
                cheese[positionY.get(i)][positionX.get(i)] = 0;

            if(!existOne)
                break;
            count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    public static void bfs(int x, int y, int n, int m, int[][] cheese, boolean[][] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visited[x][y] = true;

        int[] cordX = {0, 0, 1, -1};
        int[] cordY = {1, -1, 0, 0};

        while(!q.isEmpty()) {
            int curX = q.remove();
            int curY = q.remove();

            for(int i = 0; i < 4; i++) {
                if((curY + cordY[i] >= 0 && curY + cordY[i] < n) && ((curX + cordX[i] >= 0 && curX + cordX[i] < m))) {
                    if(!visited[curY + cordY[i]][curX + cordX[i]] && cheese[curY + cordY[i]][curX + cordX[i]] == 0) {
                        q.add(curX + cordX[i]);
                        q.add(curY + cordY[i]);
                        visited[curY + cordY[i]][curX + cordX[i]] = true;
                    }
                }
            }
        }
    }
}