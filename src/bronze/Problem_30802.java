package bronze;

import java.io.*;
import java.util.StringTokenizer;

/*
값을 입력받고 적절히 나누면 되는 간단한 문제
 */

public class Problem_30802 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        var st = new StringTokenizer(br.readLine());
        int[] arr = new int[6];
        for (int i = 0; i < 6; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int count = 0;
        for (int e : arr) {
            count += e % t == 0 ? e / t : e / t + 1;
        }

        sb.append(count).append('\n');
        sb.append(n / p).append(" ").append(n % p);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
