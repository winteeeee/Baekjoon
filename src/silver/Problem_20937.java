package silver;

import java.io.*;
import java.util.*;

/*
수의 범위가 50000이므로 수의 범위를 기준으로 카운팅한 후
떡국 그릇을 적절히 쌓아 개수를 세어주면 된다.

TreeSet으로 수의 종류를 내림차순으로 기록해두고
수가 없어질 때까지 Set을 순회하며 떡국 그릇을 만드는 과정을 반복하여
가능한 모든 떡국 그릇을 카운팅하였다.
 */

public class Problem_20937 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int totalNumber;
    static int[] count = new int[50001];
    static Set<Integer> set = new TreeSet<>((e1, e2) -> Integer.compare(e2, e1));
    static int result;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            totalNumber++;
            count[cur]++;
            set.add(cur);
        }

        while (totalNumber != 0) {
            for (int cur : set) {
                if (count[cur] != 0) {
                    count[cur]--;
                    totalNumber--;
                }
            }
            result++;
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
