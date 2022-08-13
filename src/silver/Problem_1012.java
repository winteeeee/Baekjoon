package silver;

import java.io.*;
import java.util.*;

public class Problem_1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        int earthworm = 0;
        for(int i = 0; i < t; i++, earthworm = 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[][] cabbage = new int[m+2][n+2];
            boolean[][] deployed = new boolean[m+2][n+2];

            for(int j = 0; j < m+2; j++) {
                for(int l = 0; l < n+2; l++) {
                    cabbage[j][l] = 0;
                    deployed[j][l] = false;
                }
            }

            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int column = Integer.parseInt(st.nextToken());
                cabbage[row+1][column+1] = 1;
            }

            for(int j = 1; j < m+2; j++) {
                for(int l = 1; l < n+2; l++) {
                    if(cabbage[j][l] == 1 && !deployed[j][l]) {
                        earthworm++;
                        adjoinCheck(cabbage, deployed, j, l);
                    }
                }
            }
            bw.write(String.valueOf(earthworm)+"\n");
        }
        bw.flush();
        bw.close();
    }
    public static void adjoinCheck(int[][] cabbage, boolean[][] deployed, int j, int l) {
        deployed[j][l] = true;
        while(true) {
            if ((cabbage[j - 1][l] == 0 || (cabbage[j - 1][l] == 1 && deployed[j - 1][l])) &&
                    (cabbage[j][l - 1] == 0 || (cabbage[j][l - 1] == 1 && deployed[j][l - 1])) &&
                    (cabbage[j + 1][l] == 0 || (cabbage[j + 1][l] == 1 && deployed[j + 1][l])) &&
                    (cabbage[j][l + 1] == 0 || (cabbage[j][l + 1] == 1 && deployed[j][l + 1]))) {
                return;
            }

            if (cabbage[j - 1][l] == 1 && !deployed[j - 1][l]) {
                adjoinCheck(cabbage, deployed, j - 1, l);
            } else if (cabbage[j][l - 1] == 1 && !deployed[j][l - 1]) {
                adjoinCheck(cabbage, deployed, j, l - 1);
            } else if (cabbage[j + 1][l] == 1 && !deployed[j + 1][l]) {
                adjoinCheck(cabbage, deployed, j + 1, l);
            } else if (cabbage[j][l + 1] == 1 && !deployed[j][l + 1]) {
                adjoinCheck(cabbage, deployed, j, l + 1);
            }
        }
    }
}