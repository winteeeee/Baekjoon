package gold;

import java.io.*;

public class Problem_16500 {
    static String[] a;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int n = Integer.parseInt(br.readLine());
        a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = br.readLine();
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && contain(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        bw.write(dp[s.length()] ? "1" : "0");
        bw.flush();
        bw.close();
    }

    static boolean contain(String str) {
        for (String s : a) {
            if (s.equals(str)) {
                return true;
            }
        }

        return false;
    }
}
