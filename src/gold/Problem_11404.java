package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem_11404 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] adMatrix = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j)
                    adMatrix[i][j] = 0;

                else
                    adMatrix[i][j] = 10000001;
            }
        }

        for(int k = 0; k < m; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(w < adMatrix[i][j])
                adMatrix[i][j] = w;
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if (adMatrix[i][j] > adMatrix[i][k] + adMatrix[k][j]) {
                        adMatrix[i][j] = adMatrix[i][k] + adMatrix[k][j];
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(adMatrix[i][j] < 10000001)
                    bw.write(adMatrix[i][j] + " ");

                else
                    bw.write("0 ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
