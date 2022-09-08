//110000000000 80000000000 70000000000 10000000000 50000000000 3
package platinum;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Problem_1160 {
    static long m;
    static long a;
    static long c;
    static long X0;
    static long n;
    static long g;
    static long[][] mat = new long[2][2];
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Long.parseLong(st.nextToken());
        a = Long.parseLong(st.nextToken()) % m;
        c = Long.parseLong(st.nextToken()) % m;
        X0 = Long.parseLong(st.nextToken()) % m;
        n = Long.parseLong(st.nextToken());
        g = Long.parseLong(st.nextToken());

        bw.write(String.valueOf(getXn(n) % g));
        bw.flush();
        bw.close();
    }

    public static long getXn(long n) {
        mat[0][0] = a; mat[0][1] = 0; mat[1][0] = c; mat[1][1] = 1;
        long[][] temp = new long[2][2];
        temp[0][0] = X0; temp[0][1] = 1; temp[1][0] = 0; temp[1][1] = 0;
        long[][] sum = pow(n);
        long[][] result = matrixMultiply(sum, temp);

        return result[0][0];
    }

    public static long[][] pow(long b) {
        if(b == 1)
            return mat;

        long[][] divideMat = pow(b / 2);
        if(b % 2 == 0)
            return matrixMultiply(divideMat, divideMat);
        else
            return matrixMultiply(matrixMultiply(divideMat, divideMat), mat);
    }

    public static long[][] matrixMultiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                long temp;
                for(int k = 0; k < 2; k++) {
                    temp = BigInteger.valueOf(a[k][j]).multiply(BigInteger.valueOf(b[i][k])).mod(BigInteger.valueOf(m)).longValue();
                    result[i][j] = (result[i][j] + temp) % m;
                }
            }
        }

        return result;
    }
}