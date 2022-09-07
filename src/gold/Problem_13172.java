package gold;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_13172 {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int m = Integer.parseInt(br.readLine());
        long[][] dice = new long[m + 1][2];
        long result = 0;

        for(int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dice[i][0] = Long.parseLong(st.nextToken());
            dice[i][1] = Long.parseLong(st.nextToken());
        }

        for(int i = 1; i <= m; i++) {
            long MODInverse = findMODInverse(dice[i][0], MOD - 2);
            result += ((dice[i][1] % MOD * MODInverse % MOD) % MOD);
        }

        bw.write(String.valueOf(result % MOD));
        bw.flush();
        bw.close();
    }

    public static long findMODInverse(long target, long n) {
        if(n == 1)
            return target;

        long result;
        long temp = findMODInverse(target, n / 2);
        if(n % 2 == 0)
            result = temp % MOD * temp % MOD % MOD;
        else
            result = temp % MOD * temp % MOD * target % MOD % MOD;

        return result;
    }
}
