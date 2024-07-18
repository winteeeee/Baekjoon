package gold;

import java.io.*;

public class Problem_15717 {
    static final long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Long.parseLong(br.readLine());

        bw.write(String.valueOf(findAnswer(n - 1) % MOD));
        bw.flush();
        bw.close();
    }

    public static long findAnswer(long n) {
        if (n == 1) {
            return 2;
        } else if (n <= 0) {
            return 1;
        }

        if (n % 2 == 0) {
            long middleAnswer = findAnswer(n / 2);
            return (middleAnswer % MOD * middleAnswer % MOD) % MOD;
        } else {
            long middleAnswer = findAnswer((n - 1) / 2);
            return (middleAnswer % MOD * middleAnswer % MOD * 2 % MOD) % MOD;
        }
    }
}
