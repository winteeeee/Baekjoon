package gold;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem_20181 {
    static class Segment {
        int start;
        int end;
        long value;

        public Segment(int start, int end, long value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] feed = new int[n];
        for (int i = 0; i < n; i++) {
            feed[i] = Integer.parseInt(st.nextToken());
        }

        var list = new ArrayList<Segment>();
        for (int i = 0; i < n; i++) {
            long prevEnergy = 0;
            int j = i - 1;
            for (; j >= 0; j--) {
                if (prevEnergy + feed[j] >= k)
                    break;

                prevEnergy += feed[j];
            }

            long value = prevEnergy + (long) feed[i] - (long) k;
            if (value > 0)
                list.add(new Segment(j + 1, i, value));
        }

        if (!list.isEmpty()) {
            long[] dp = new long[list.size()];
            dp[0] = list.get(0).value;
            long max = dp[0];

            for (int i = 1; i < list.size(); i++) {
                Segment cur = list.get(i);
                dp[i] = cur.value;

                for (int j = i - 1; j >= 0; j--) {
                    Segment prev = list.get(j);
                    if (prev.end < cur.start) {
                        dp[i] += dp[j];
                        break;
                    }
                }

                dp[i] = Math.max(dp[i], dp[i - 1]);
                max = Math.max(dp[i], max);
            }

            bw.write(String.valueOf(max));
        } else {
            bw.write("0");
        }

        bw.flush();
        bw.close();
    }
}
