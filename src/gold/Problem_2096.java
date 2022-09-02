package gold;

import java.io.*;
import java.util.*;

public class Problem_2096 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        int[][] maxDP = new int[n][3];
        int[][] minDP = new int[n][3];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 3; i++) {
            maxDP[0][i] = arr[0][i];
            minDP[0][i] = arr[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if(j == 0) {
                    maxDP[i][j] = arr[i][j] + Math.max(maxDP[i - 1][j], maxDP[i - 1][j + 1]);
                    minDP[i][j] = arr[i][j] + Math.min(minDP[i - 1][j], minDP[i - 1][j + 1]);
                }

                else if(j == 2) {
                    maxDP[i][j] = arr[i][j] + Math.max(maxDP[i - 1][j], maxDP[i - 1][j - 1]);
                    minDP[i][j] = arr[i][j] + Math.min(minDP[i - 1][j], minDP[i - 1][j - 1]);
                }

                else {
                    int min = Integer.MAX_VALUE;
                    int max = Integer.MIN_VALUE;

                    for(int k = 0; k < 3; k++) {
                        if(max < maxDP[i - 1][k])
                            max = maxDP[i - 1][k];

                        if(min > minDP[i - 1][k])
                            min = minDP[i - 1][k];
                    }

                    maxDP[i][j] = arr[i][j] + max;
                    minDP[i][j] = arr[i][j] + min;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int k = 0; k < 3; k++) {
            if(max < maxDP[n - 1][k])
                max = maxDP[n - 1][k];

            if(min > minDP[n - 1][k])
                min = minDP[n - 1][k];
        }

        bw.write(max + " " + min);
        bw.flush();
        bw.close();
    }
}
