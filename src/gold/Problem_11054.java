package gold;

import java.io.*;
import java.util.StringTokenizer;

/*
재채점으로 틀려서 기존 코드를 고쳐서 다시 풀었다.
그냥 DP 테이블은 이해가 갔는데 DP2를 왜 저렇게 채우는지 이해가 되지 않아 DP2 부분을 다시 풀었다.

DP1을 해당 원소까지 보았을 때 가장 큰 증가하는 부분 수열의 길이를 저장해두고
DP2은 끝에서 첫 번째 원소로 가며 가장 큰 증가하는 부분 수열의 길이를 저장한다.

DP1 + DP2의 합이 최대인 것이 답이다.
 */

public class Problem_11054 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n + 1];
        int[] dp = new int[n + 1];
        int[] dp2 = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if(sequence[i] > sequence[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        for(int i = n; i >= 0; i--) {
            for (int j = n; j > i; j--) {
                if(sequence[i] > sequence[j]) {
                    dp2[i] = Math.max(dp2[j] + 1, dp2[i]);
                }
            }
        }


        int result = 0;
        for (int i = 1; i <= n; i++)
            result = Math.max(dp[i] + dp2[i], result);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}

