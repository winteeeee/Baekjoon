package silver;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Problem_1735 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int a1 = Integer.parseInt(st.nextToken());
        int b1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int a2 = Integer.parseInt(st.nextToken());
        int b2 = Integer.parseInt(st.nextToken());

        int resultA = a1 * b2 + a2 * b1;
        int resultB = b1 * b2;
        int gcd = BigInteger.valueOf(resultA).gcd(BigInteger.valueOf(resultB)).intValue();

        bw.write(resultA / gcd + " " + resultB / gcd);
        bw.flush();
        bw.close();
    }
}
