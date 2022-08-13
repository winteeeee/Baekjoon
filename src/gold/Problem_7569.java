package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_7569 {
    static Queue<Integer> q = new LinkedList<>();
    static int count = 0;
    static boolean first = true;
    static Integer[] xArr;
    static Integer[] yArr;
    static Integer[] zArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][][] tomato = new int[n+1][m+1][h];

        for(int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        ArrayList<Integer> xArrList = new ArrayList<>();
        ArrayList<Integer> yArrList = new ArrayList<>();
        ArrayList<Integer> zArrList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < h; k++) {
                    if (tomato[i][j][k] == 1) {
                        xArrList.add(j);
                        yArrList.add(i);
                        zArrList.add(k);
                    }
                }
            }
        }
        xArr = xArrList.toArray(new Integer[0]);
        yArr = yArrList.toArray(new Integer[0]);
        zArr = zArrList.toArray(new Integer[0]);
        bfs(0, 0, 0, tomato, n, m, h);

        boolean exist0 = false;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < h; k++) {
                    if (tomato[i][j][k] == 0)
                        exist0 = true;
                }
            }
        }

        if(exist0)
            bw.write(String.valueOf(-1));

        else
            bw.write(String.valueOf(count));

        bw.flush();
        bw.close();
    }

    public static void bfs(int x, int y, int z, int[][][] tomato, int n, int m, int h) {
        if(first) {
            for(int i = 0; i < xArr.length; i++) {
                for(int j = 0; j < h; j++) {
                    q.add(yArr[i]);
                    q.add(xArr[i]);
                    q.add(zArr[i]);
                    q.add(0);
                }
            }
        }
        else
            tomato[y][x][z] = 1;

        while(!q.isEmpty()) {
            int tempy = q.remove();
            int tempx = q.remove();
            int tempz = q.remove();
            if(count != q.peek())
                count++;
            q.remove();

            if (tempy + 1 < n) {
                if (tomato[tempy + 1][tempx][tempz] == 0) {
                    tomato[tempy + 1][tempx][tempz] = 1;
                    q.add(tempy + 1);
                    q.add(tempx);
                    q.add(tempz);
                    q.add(count+1);
                }
            }

            if (tempy - 1 >= 0) {
                if (tomato[tempy - 1][tempx][tempz] == 0) {
                    tomato[tempy - 1][tempx][tempz] = 1;
                    q.add(tempy - 1);
                    q.add(tempx);
                    q.add(tempz);
                    q.add(count+1);
                }
            }

            if (tempx + 1 < m) {
                if (tomato[tempy][tempx + 1][tempz] == 0) {
                    tomato[tempy][tempx + 1][tempz] = 1;
                    q.add(tempy);
                    q.add(tempx + 1);
                    q.add(tempz);
                    q.add(count+1);
                }
            }

            if (tempx - 1 >= 0) {
                if (tomato[tempy][tempx - 1][tempz] == 0) {
                    tomato[tempy][tempx - 1][tempz] = 1;
                    q.add(tempy);
                    q.add(tempx - 1);
                    q.add(tempz);
                    q.add(count+1);
                }
            }

            if(tempz + 1 < h) {
                if (tomato[tempy][tempx][tempz+1] == 0) {
                    tomato[tempy][tempx][tempz+1] = 1;
                    q.add(tempy);
                    q.add(tempx);
                    q.add(tempz+1);
                    q.add(count+1);
                }
            }

            if(tempz - 1 >= 0) {
                if (tomato[tempy][tempx][tempz-1] == 0) {
                    tomato[tempy][tempx][tempz-1] = 1;
                    q.add(tempy);
                    q.add(tempx);
                    q.add(tempz-1);
                    q.add(count+1);
                }
            }
        }
    }
}
