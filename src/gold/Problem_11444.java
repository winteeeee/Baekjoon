package gold;

import java.io.*;
public class Problem_11444 {
    static long[][] fibo1 = {{1, 1}, {1, 0}};
    static final int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Long.parseLong(br.readLine());

        bw.write(String.valueOf(fibonacci(n)[0][1] % MOD));
        bw.flush();
        bw.close();
    }
//1000000000000000000
    public static long[][] fibonacci(long n) {
        if(n == 1)
            return fibo1;

        long[][] result;
        long[][] parameter = fibonacci(n / 2);
        if(n % 2 == 0)
            result = multi(parameter, parameter);
        else
            result = multi(multi(parameter, parameter), fibo1);

        return result;
    }

    public static long[][] multi(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = (a[0][0] % MOD * b[0][0] % MOD + a[0][1] % MOD * b[1][0] % MOD) % MOD;
        result[0][1] = (a[0][0] % MOD * b[1][0] % MOD + a[0][1] % MOD * b[1][1] % MOD) % MOD;
        result[1][0] = (a[1][0] % MOD * b[0][0] % MOD + a[1][1] % MOD * b[1][0] % MOD) % MOD;
        result[1][1] = (a[1][0] % MOD * b[0][1] % MOD + a[1][1] % MOD * b[1][1] % MOD) % MOD;

        return result;
    }
}
