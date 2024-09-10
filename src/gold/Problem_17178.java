package gold;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_17178 {
    static int n;
    static String[][] queue;
    static String[] copyQueue;
    static final String GOOD = "GOOD";
    static final String BAD = "BAD";

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        queue = new String[n][5];
        copyQueue = new String[5 * n];
        for (int i = 0; i < n; i++) {
            var st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                queue[i][j] = st.nextToken();
            }
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                copyQueue[idx++] = queue[i][j];
            }
        }
        Arrays.sort(copyQueue, (s1, s2) -> {
            String[] split1 = split(s1);
            String[] split2 = split(s2);

            int compare = split1[0].compareTo(split2[0]);
            if (compare == 0) return split1[1].compareTo(split2[1]);
            return compare;
        });

        idx = 0;
        var s = new Stack<String>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                s.add(queue[i][j]);

                while (!s.isEmpty() && s.peek().equals(copyQueue[idx])) {
                    s.pop();
                    idx++;
                }
            }
        }

        bw.write(s.isEmpty() ? GOOD : BAD);
        bw.flush();
        bw.close();
    }

    static String[] split(String s) {
        String[] result = s.split("-");
        while (result[1].length() < 3) {
            result[1] = '0' + result[1];
        }

        return result;
    }
}
