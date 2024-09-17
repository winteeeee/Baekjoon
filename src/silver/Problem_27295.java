package silver;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Problem_27295 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        long sumX = 0;
        long sumY = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            sumX += Long.parseLong(st.nextToken());
            sumY += Long.parseLong(st.nextToken());
        }

        String a1;
        if (sumX == 0) {
            a1 = "EZPZ";
        } else if ((sumY - (long) b * n) % sumX == 0) {
            a1 = String.valueOf((sumY - (long) b * n) / sumX);
        } else {
            BigInteger p = BigInteger.valueOf((sumY - (long) b * n));
            BigInteger q = BigInteger.valueOf(sumX);
            BigInteger gcd = p.gcd(q);

            p = p.divide(gcd);
            q = q.divide(gcd);
            boolean isMinus = false;
            if (p.compareTo(BigInteger.ZERO) < 0) {
                p = p.multiply(BigInteger.valueOf(-1));
                isMinus = true;
            }

            if (q.compareTo(BigInteger.ZERO) < 0) {
                q = q.multiply(BigInteger.valueOf(-1));
                isMinus = !isMinus;
            }

            if (isMinus) {
                a1 = "-" + p + "/" + q;
            } else {
                a1 = p + "/" + q;
            }
        }

        bw.write(a1);
        bw.flush();
        bw.close();
    }
}
