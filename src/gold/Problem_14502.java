package gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_14502 {
    static int[] cordX = {0, 0, 1, -1};
    static int[] cordY = {1, -1, 0, 0};
    static int n;
    static int m;
    static int count = 0;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] lab = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        fortification(lab, 0);
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }

    public static void fortification(int[][] lab, int wall) {
        if(wall == 3) {
            int[][] cloneLab = new int[n][m];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++)
                    cloneLab[i][j] = lab[i][j];
            }
            boolean[][] visited = new boolean[n][m];
            boolean[][] visited2 = new boolean[n][m];

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(cloneLab[i][j] == 2 && !visited[i][j]) {
                        infectingLab(i, j, cloneLab, visited);
                    }
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(cloneLab[i][j] == 0 && !visited2[i][j]) {
                        countingSafeZone(i, j, cloneLab, visited2);
                    }
                }
            }

            if(count > max)
                max = count;

            count = 0;

            return;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(lab[i][j] == 0) {
                    lab[i][j] = 1;
                    fortification(lab, wall + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }

    public static void infectingLab(int row, int column, int[][] lab, boolean[][] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(row);
        q.add(column);
        visited[row][column] = true;

        while(!q.isEmpty()) {
            int r = q.remove();
            int c = q.remove();

            for(int i = 0; i < 4; i++) {
                if((r + cordY[i] >= 0 && r + cordY[i] < n) && (c + cordX[i] >= 0 && c + cordX[i] < m)) {
                    if(!visited[r + cordY[i]][c + cordX[i]] && lab[r + cordY[i]][c + cordX[i]] == 0) {
                        q.add(r + cordY[i]);
                        q.add(c + cordX[i]);
                        lab[r + cordY[i]][c + cordX[i]] = 2;
                        visited[r + cordY[i]][c + cordX[i]] = true;
                    }
                }
            }
        }
    }

    public static void countingSafeZone(int row, int column, int[][] lab, boolean[][] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(row);
        q.add(column);
        visited[row][column] = true;

        while(!q.isEmpty()) {
            int r = q.remove();
            int c = q.remove();
            count++;

            for(int i = 0; i < 4; i++) {
                if((r + cordY[i] >= 0 && r + cordY[i] < n) && (c + cordX[i] >= 0 && c + cordX[i] < m)) {
                    if(!visited[r + cordY[i]][c + cordX[i]] && lab[r + cordY[i]][c + cordX[i]] == 0) {
                        q.add(r + cordY[i]);
                        q.add(c + cordX[i]);
                        visited[r + cordY[i]][c + cordX[i]] = true;
                    }
                }
            }
        }
    }
}