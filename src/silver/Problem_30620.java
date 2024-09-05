package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_30620 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());

        bw.write("2\n" + (x * y - x) + '\n' + '-' + ((x - 1) * y) + '\n');
        bw.flush();
        bw.close();
    }
}
