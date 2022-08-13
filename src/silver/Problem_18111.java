package silver;

import java.io.*;
import java.util.*;

public class Problem_18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[][] ground = new int[n][m];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                ground[i][j] = temp;

                if(temp < min)
                    min = temp;

                if(temp > max)
                    max = temp;
            }
        }

        int second = 0;
        int result = Integer.MAX_VALUE;
        int bTemp = b;
        int result2 = 0;
        FOR1:
        for(int i = min; i <= max; i++, second = 0, b = bTemp) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    if(ground[j][k] > i) {
                        second += (ground[j][k] - i)*2;
                        b += ground[j][k] - i;
                    }
                }
            }

            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    if(ground[j][k] < i) {
                        if(b >= i - ground[j][k]) {
                            second += i - ground[j][k];
                            b -= i - ground[j][k];
                        }

                        else
                            continue FOR1;
                    }
                }
            }

            if(second <= result) {
                result = second;
                result2 = i;
            }
        }
        bw.write(result + " " + result2);
        bw.flush();
        bw.close();
    }
}