package gold;

import java.io.*;
import java.util.*;

public class Problem_2637 {
    static TreeMap<Integer, Integer>[] maps;
    static TreeMap<Integer, Integer>[] dp;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        maps = new TreeMap[n + 1];
        for (int i = 1; i <= n; i++)
            maps[i] = new TreeMap<>();

        for (int i = 0; i < m; i++) {
            var st = new StringTokenizer(br.readLine());
            int component = Integer.parseInt(st.nextToken());
            int need = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            maps[component].put(need, maps[component].getOrDefault(need, 0) + count);
        }

        dp = new TreeMap[n + 1];
        for (int i = 1; i <= n; i++)
            dp[i] = new TreeMap<>();
        for (int i = 1; i <= n; i++)
            getDpValue(i);

        var sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> e : dp[n].entrySet())
            sb.append(e.getKey()).append(' ').append(e.getValue()).append(" \n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void getDpValue(int i) {
        //이미 테이블이 채워진 경우라면 다시 구하지 않음
        if (!dp[i].isEmpty())
            return;

        if (maps[i].isEmpty()) {
            dp[i].put(i, 1);
            return;
        }

        for (Map.Entry<Integer, Integer> e : maps[i].entrySet()) {
            int idx = e.getKey();
            int count = e.getValue();

            if (dp[idx].isEmpty())
                getDpValue(idx);

            for (Map.Entry<Integer, Integer> e2 : dp[idx].entrySet()) {
                dp[i].put(e2.getKey(), dp[i].getOrDefault(e2.getKey(), 0) + (e2.getValue() * count));
            }
        }
    }
}
