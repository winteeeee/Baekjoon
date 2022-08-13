package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_7576 {
    static Queue<Integer> q = new LinkedList<>();
    static int count = 0;
    static boolean first = true;
    static Integer[] xArr;
    static Integer[] yArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] tomato = new int[n+1][m+1];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Integer> xArrList = new ArrayList<>();
        ArrayList<Integer> yArrList = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(tomato[i][j] == 1) {
                    xArrList.add(j);
                    yArrList.add(i);
                }
            }
        }
        xArr = xArrList.toArray(new Integer[0]);
        yArr = yArrList.toArray(new Integer[0]);
        bfs(0, 0, tomato, n, m);

        boolean exist0 = false;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(tomato[i][j] == 0)
                    exist0 = true;
            }
        }

        if(exist0)
            bw.write(String.valueOf(-1));

        else
            bw.write(String.valueOf(count));

        bw.flush();
        bw.close();
    }

    public static void bfs(int x, int y, int[][] tomato, int n, int m) {
        if(first) {
            for(int i = 0; i < xArr.length; i++) {
                q.add(yArr[i]);
                q.add(xArr[i]);
                q.add(0);
            }
        }
        else
            tomato[y][x] = 1;

        while(!q.isEmpty()) {
            int tempy = q.remove();
            int tempx = q.remove();
            if(count != q.peek())
                count++;
            q.remove();

            if (tempy + 1 < n) {
                if (tomato[tempy + 1][tempx] == 0) {
                    tomato[tempy + 1][tempx] = 1;
                    q.add(tempy + 1);
                    q.add(tempx);
                    q.add(count+1);
                }
            }

            if (tempy - 1 >= 0) {
                if (tomato[tempy - 1][tempx] == 0) {
                    tomato[tempy - 1][tempx] = 1;
                    q.add(tempy - 1);
                    q.add(tempx);
                    q.add(count+1);
                }
            }

            if (tempx + 1 < m) {
                if (tomato[tempy][tempx + 1] == 0) {
                    tomato[tempy][tempx + 1] = 1;
                    q.add(tempy);
                    q.add(tempx + 1);
                    q.add(count+1);
                }
            }

            if (tempx - 1 >= 0) {
                if (tomato[tempy][tempx - 1] == 0) {
                    tomato[tempy][tempx - 1] = 1;
                    q.add(tempy);
                    q.add(tempx - 1);
                    q.add(count+1);
                }
            }
        }
    }
}
