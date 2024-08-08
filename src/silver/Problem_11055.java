package silver;

import java.io.*;
import java.util.StringTokenizer;

/*
G4 2296 문제에서 배웠던 것처럼 P 테이블에 무조건 최댓값이 들어있지 않을 수 있다.
라는 점을 활용했던 문제

DP[i]: i번째 원소를 '반드시' 포함하는 가장 큰 증가하는 부분 수열의 합
으로 DP 테이블을 정의하고 배열을 순회하며 증가하는 부분 수열의 원소를 만족할 때
테이블의 값을 갱신해준다.
 */

public class Problem_11055 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int result = arr[0];
        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i] = arr[i];
            for (int j = 0; j < i; j++) {
                dp[i] = arr[j] < arr[i] ? Math.max(dp[i], dp[j] + arr[i]) : dp[i];
            }
            result = Math.max(result, dp[i]);
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
