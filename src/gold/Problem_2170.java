package gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_2170 {
    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        double distance;

        Pair(String str) {
            var st = new StringTokenizer(str);
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(x, o.x);
        }
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(br.readLine());
        }

        Arrays.sort(arr);
        long result = 0;
        Pair cur = arr[0];
        for (int i = 1; i < n; i++) {
            Pair next = arr[i];

            if (next.x < cur.y) {
                cur.y = Math.max(cur.y, next.y);
            } else {
                result += ((long)cur.y - (long)cur.x);
                cur = next;
            }
        }
        result += ((long)cur.y - (long)cur.x);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
