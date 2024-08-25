package platinum;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_24518 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final long MOD = 1000000007;

        var st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long curF = n;
        long curM = 1 % m;
        long result = 0;
        for (int i = 1; curF != 0;) {
            long sectionLength = n / curF - i + 1;
            result = (result % MOD + curF % MOD * getSumOfG(curM, m, sectionLength, MOD) % MOD) % MOD;

            curF = n / (i + sectionLength);
            curM = (curM + sectionLength) % m;
            i += (int) sectionLength;
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static long getSumOfG(long start, long m, long length, long mod) {
        long lastNumber = start + length > m ? m - 1 : start + length - 1;
        long result = (lastNumber - start + 1) * (start + lastNumber) / 2;
        length -= (lastNumber - start + 1);

        long iter = length / m;
        result += ((m - 1) * m / 2) * iter;
        length -= (m * iter);

        result += (length - 1) * length / 2;
        result %= mod;

        return result;
    }
}
