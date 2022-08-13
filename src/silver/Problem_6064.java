package silver;

import java.io.*;
import java.util.StringTokenizer;

public class Problem_6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        FOR1:
        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int LCM = m * n / GCD(m, n);

            for (int j = 0; j < LCM / m; j++) {
                int year = x + m * j;

                if (((year - 1) % n) + 1 == y) {
                    sb.append(year + "\n");
                    continue FOR1;
                }
            }
            sb.append(-1 + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int GCD(int a, int b) {
        int r = a % b;

        if(r == 0)
            return b;

        return GCD(b, r);
    }
}
