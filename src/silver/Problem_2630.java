package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_2630 {
    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] paper = new int[n][n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }

        divide(n, 0, 0, paper);
        bw.write(String.valueOf(white)+"\n");
        bw.write(String.valueOf(blue));
        bw.flush();
        bw.close();
    }

    public static void divide(int n, int x, int y, int[][] paper) {
        int temp = paper[x][y];
        for(int i = y; i < y+n; i++) {
            for(int j = x; j < x+n; j++) {
                if(temp != paper[j][i]) {
                    divide(n / 2, x, y, paper);
                    divide(n / 2, x+n/2, y, paper);
                    divide(n / 2, x, y+n/2, paper);
                    divide(n / 2, x+n/2, y+n/2, paper);
                    return;
                }
            }
        }

        if(temp == 1)
            blue++;

        else
            white++;
    }
}
