package gold;

import java.io.*;

/*
타일링 문제는 전형적인 DP 문제 유형이므로 DP로 푼다는 것 자체는 빠르게 유추할 수 있다.

dp[2]인 경우를 초기화해주고 (그래서 예제 입력이 2로 주어진 것 같다)
이후의 경우를 생각해보면 일단 i가 홀수인 경우는 3칸의 타일을 정확하게 채울 수 없으므로
경우의 수는 무조건 0이다. 따라서 우리는 짝수의 경우만 고려하면 된다.

현재 칸에 2칸짜리 타일을 두는 경우는
ㅡㅡ    ㅣㅣ  ㅡㅡ
ㅡㅡ    ㅣㅣ  ㅣㅣ
ㅡㅡ    ㅡㅡ  ㅣㅣ
이렇게 3가지 종류고

이후 4칸, 6칸, 8칸... 으로 짝수번째가 될때마다
ㅡㅡ(n번 반복)
ㅣㅡㅡ (n번 반복)ㅣ
ㅣㅡㅡ (n번 반복)ㅣ

이러한 모양이 추가된다. 이때 맨 위쪽 블록은 맨 아래쪽으로도 갈 수 있으므로
이러한 타일의 경우의 수는 2가지가 된다.

따라서 먼저 2칸짜리 타일을 고르는 경우(dp[i - 2])에 * 3을 해주고
새로운 타일이 추가되는 dp[i - 4], dp[i - 6] ....에 * 2를 해주며 더해주고
마지막으로 자신의 차례에 추가되는 짝수 번째 타일 2가지를 더해주면 된다.
 */

public class Problem_2133 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dp = new int[31];

        dp[2] = 3;
        for (int i = 4; i <= n; i += 2) {
            dp[i] += dp[i - 2] * 3;
            for (int j = i - 4; j > 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
            dp[i] += 2;
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
        bw.close();
    }
}
