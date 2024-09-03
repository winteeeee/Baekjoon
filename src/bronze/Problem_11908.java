package bronze;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_11908 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int max = 0;
        int sum = 0;
        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            max = Math.max(max, cur);
            sum += cur;
        }

        bw.write(String.valueOf(sum - max));
        bw.flush();
        bw.close();
    }
}
