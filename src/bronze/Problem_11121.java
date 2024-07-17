package bronze;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_11121 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            var input = st.nextToken();
            var output = st.nextToken();

            if (input.equals(output)) {
                sb.append("OK\n");
            } else {
                sb.append("ERROR\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
