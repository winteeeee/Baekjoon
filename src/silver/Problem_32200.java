package silver;

import java.io.*;
import java.util.*;

public class Problem_32200 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[] sandwiches = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int a = 0, b = 0;
        for (int sandwich : sandwiches) {
            a += (sandwich / x == sandwich / y ? sandwich / y : sandwich / x);
            b += (sandwich / x == sandwich / y ? sandwich % y : 0);
        }

        bw.write(a + "\n" + b);
        bw.flush();
        bw.close();
    }
}
