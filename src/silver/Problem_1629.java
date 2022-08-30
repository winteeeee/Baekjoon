package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());
        long result = pow(a, b, c);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static long pow(long a, long b, long c) {
        long result;
        if(b == 0) {
            return 1;
        }

        if(b % 2 == 0) {
            long n = pow(a, b / 2, c);
            result = n % c * n % c % c;
        }

        else {
            long n = pow(a, (b - 1) / 2, c);
            result = n % c * n %c * a %c %c;
        }

        return result;
    }
}