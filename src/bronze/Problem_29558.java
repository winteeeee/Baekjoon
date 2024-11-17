package bronze;

import java.io.*;
import java.util.*;

public class Problem_29558 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        var sb = new StringBuilder();
        int[] result = new int[n];
        int base = d - (n - 1) / 2;
        for (int i = 0; i < n; i++) {
            result[i] = base + i;
        }

        int sum = Arrays.stream(result).sum();
        int diff = d * n - sum;
        result[0] += diff;

        for (int i = 0; i < n; i++)
            sb.append(result[i]).append(' ');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
