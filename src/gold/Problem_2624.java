package gold;

import java.io.*;
import java.util.*;

public class Problem_2624 {
    static class Coin {
        int p;
        int n;

        Coin(int p, int n) {
            this.p = p;
            this.n = n;
        }
    }
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        var coins = new Coin[k];
        for (int i = 0; i < k; i++) {
            var st = new StringTokenizer(br.readLine());
            coins[i] = new Coin(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        var dp = new int[t + 1];
        dp[0] = 1;
        for (Coin c : coins) {
            for (int i = t; i >= 0; i--) {
                for (int j = c.n; j >= 1; j--) {
                    if (i - c.p * j >= 0) {
                        dp[i] += dp[i - c.p * j];
                    }
                }
            }
        }

        bw.write(String.valueOf(dp[t]));
        bw.flush();
        bw.close();
    }
}
