package bronze;

import java.io.*;
import java.util.Arrays;

public class Problem_2204 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var sb = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            var words = new String[n];
            for (int i = 0; i < n; i++)
                words[i] = br.readLine();

            Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
            sb.append(words[0]).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
