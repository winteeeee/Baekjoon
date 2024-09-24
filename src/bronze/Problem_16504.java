package bronze;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_16504 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long result = 0;
        for (int i = 0; i < n; i++) {
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                result += Long.parseLong(st.nextToken());
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
