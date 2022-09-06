package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_10830 {
    static long[][] originalMat;
    static int n;
    static final int MOD = 1000;
    public static void main(String[] args) throws IOException {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long[][] matrix = new long[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++)
                matrix[i][j] = Long.parseLong(st.nextToken());
        }
        originalMat = matrix.clone();
        matrix = pow(matrix, b);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(b != 1)
                    bw.write(matrix[i][j] + " ");

                else
                    bw.write(matrix[i][j] % MOD + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static long[][] pow(long[][] matrix, long b) {
        if(b == 1)
            return originalMat;

        if(b % 2 == 0) {
            long[][] divideMat = pow(matrix, b / 2);
            matrix = multiply(divideMat, divideMat);
        }

        else {
            long[][] divideMat = pow(matrix, b / 2);
            matrix = multiply(multiply(divideMat, divideMat), originalMat);
        }

        return matrix;
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                long temp = 0;
                for(int k = 0; k < n; k++) {
                    temp += ((a[k][j] % MOD * b[i][k] % MOD) % MOD);
                }
                result[i][j] = temp % MOD;
            }
        }

        return result;
    }
}
