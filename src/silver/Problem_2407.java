package silver;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Problem_2407 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        bw.write(combination(n, m).toString());
        bw.flush();
        bw.close();
    }

    public static BigInteger combination(int n, int m) {
        BigInteger t1 = new BigInteger("1");
        BigInteger t2 = new BigInteger("1");

        for(int i = 0; i < m; i++) {
            t1 = t1.multiply(BigInteger.valueOf(n - i));
        }

        for(int i = 0; i < m; i++) {
            t2 = t2.multiply(BigInteger.valueOf(m - i));
        }

        return t1.divide(t2);
    }
}
