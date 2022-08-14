package gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_14500 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] paper = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }

        int[] sum = new int[5];
        sum[0] = tetromino1(n, m, paper);
        sum[1] = tetromino2(n, m, paper);
        sum[2] = tetromino3(n, m, paper);
        sum[3] = tetromino4(n, m, paper);
        sum[4] = tetromino5(n, m, paper);
        Arrays.sort(sum);

        bw.write(String.valueOf(sum[4]));
        bw.flush();
        bw.close();
    }

    public static int tetromino1(int n, int m, int[][] paper) { //ㅣ 모양 테트로미노
        int result = 0;

        for(int i = 0; i < n - 3; i++) {
            for(int j = 0; j < m; j++) {
                int temp = paper[i][j] + paper[i + 1][j] + paper[i + 2][j] + paper[i + 3][j];

                if(temp > result)
                    result = temp;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m - 3; j++) {
                int temp = paper[i][j] + paper[i][j + 1] + paper[i][j + 2] + paper[i][j + 3];

                if(temp > result)
                    result = temp;
            }
        }

        return result;
    }

    public static int tetromino2(int n, int m, int[][] paper) { //ㅁ 모양 테트로미노
        int result = 0;

        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < m - 1; j++) {
                int temp = paper[i][j] + paper[i + 1][j] + paper[i][j + 1] + paper[i + 1][j + 1];

                if(temp > result)
                    result = temp;
            }
        }

        return result;
    }

    public static int tetromino3(int n, int m, int[][] paper) { //L 모양 테트로미노
        int result = 0;

        for(int i = 0; i < n - 2; i++) {
            for(int j = 0; j < m; j++) {
                int temp = paper[i][j] + paper[i + 1][j] + paper[i + 2][j];
                int max = Integer.MIN_VALUE;

                for(int k = 0; k < 4; k++) {
                    if(j - 1 >= 0) {
                        if (paper[i][j - 1] > max)
                            max = paper[i][j - 1];

                        if(paper[i + 2][j - 1] > max)
                            max = paper[i + 2][j - 1];
                    }

                    if(j + 1 < m) {
                        if (paper[i][j + 1] > max)
                            max = paper[i][j + 1];

                        if (paper[i + 2][j + 1] > max)
                            max = paper[i + 2][j + 1];
                    }
                }

                temp += max;

                if(temp > result)
                    result = temp;
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m - 2; j++) {
                int temp = paper[i][j] + paper[i][j + 1] + paper[i][j + 2];
                int max = Integer.MIN_VALUE;

                for(int k = 0; k < 4; k++) {
                    if(i - 1 >= 0) {
                        if (paper[i - 1][j] > max)
                            max = paper[i - 1][j];

                        if(paper[i - 1][j + 2] > max)
                            max = paper[i - 1][j + 2];
                    }

                    if(i + 1 < n) {
                        if (paper[i + 1][j] > max)
                            max = paper[i + 1][j];

                        if (paper[i + 1][j + 2] > max)
                            max = paper[i + 1][j + 2];
                    }
                }

                temp += max;

                if(temp > result)
                    result = temp;
            }
        }

        return result;
    }

    public static int tetromino4(int n, int m, int[][] paper) { // N 모양 테트로미노
        int result = 0;

        for(int i = 0; i < n - 2; i++) {
            for(int j = 0; j < m - 1; j++) {
                int temp1 = paper[i][j] + paper[i + 1][j] + paper[i + 1][j + 1] + paper[i + 2][j + 1];
                int temp2 = paper[i][j + 1] + paper[i + 1][j + 1] + paper[i + 1][j] + paper[i + 2][j];

                if(temp1 > temp2) {
                    if (temp1 > result)
                        result = temp1;
                }

                else {
                    if (temp2 > result)
                        result = temp2;
                }
            }
        }

        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < m - 2; j++) {
                int temp1 = paper[i][j] + paper[i][j + 1] + paper[i + 1][j + 1] + paper[i + 1][j + 2];
                int temp2 = paper[i + 1][j] + paper[i + 1][j + 1] + paper[i][j + 1] + paper[i][j + 2];

                if(temp1 > temp2) {
                    if (temp1 > result)
                        result = temp1;
                }

                else {
                    if (temp2 > result)
                        result = temp2;
                }
            }
        }

        return result;
    }

    public static int tetromino5(int n, int m, int[][] paper) { // ㅗ 모양 테트로미노
        int result = 0;

        for(int i =  0; i < n; i++) {
            for(int j = 0; j < m - 2; j++) {
                int temp = paper[i][j] + paper[i][j + 1] + paper[i][j + 2];

                if(i == 0) {
                    temp += paper[i + 1][j + 1];
                }

                else if(i == n - 1) {
                    temp += paper[i - 1][j + 1];
                }

                else {
                    if (paper[i + 1][j + 1] > paper[i - 1][j + 1])
                        temp += paper[i + 1][j + 1];

                    else
                        temp += paper[i - 1][j + 1];
                }

                if(temp > result)
                    result = temp;
            }
        }

        for(int i = 0; i < n - 2; i++) {
            for(int j = 0; j < m; j++) {
                int temp = paper[i][j] + paper[i + 1][j] + paper[i + 2][j];

                if(j == 0) {
                    temp += paper[i + 1][j + 1];
                }

                else if(j == m - 1) {
                    temp += paper[i + 1][j - 1];
                }

                else {
                    if (paper[i + 1][j - 1] > paper[i + 1][j + 1])
                        temp += paper[i + 1][j - 1];

                    else
                        temp += paper[i + 1][j + 1];
                }

                if(temp > result)
                    result = temp;
            }
        }
        return result;
    }
}
