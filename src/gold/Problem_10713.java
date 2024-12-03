package gold;

import java.io.*;
import java.util.*;

public class Problem_10713 {
    static class Plan {
        int from;
        int to;

        Plan(int f, int t) {
            from = f;
            to = t;
        }
    }

    static class Railway {
        long a;
        long b;
        long c;

        Railway(String s) {
            var st = new StringTokenizer(s);
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());
        }

        long calcFee(int cnt) {
            return Math.min(a * cnt, c + b * cnt);
        }
    }

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Plan[] plans = new Plan[m - 1];
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        plans[0] = new Plan(from, to);
        for (int i = 1; i < m - 1; i++) {
            from = to;
            to = Integer.parseInt(st.nextToken());
            plans[i] = new Plan(from, to);
        }

        Railway[] railways = new Railway[n];
        for (int i = 1; i < n; i++) {
            railways[i] = new Railway(br.readLine());
        }

        int[] count = new int[n + 1];
        for (Plan p : plans) {
            count[Math.min(p.from, p.to)]++;
            count[Math.max(p.from, p.to)]--;
        }

        long ans = 0;
        int frequency = 0;
        for (int i = 1; i < n; i++) {
            frequency += count[i];
            ans += railways[i].calcFee(frequency);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}
