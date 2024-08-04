package gold;

import java.io.*;
import java.util.ArrayList;

/*
수업 시간에 배운 동전 DP에 소수 판정을 살짝 섞은 DP문제
경우의 수가 매우 많다는 점과 그리디스럽게 처리하지 못할 것 같다는 직관과 함께
DP라는 유형 자체는 빠르게 캐치했지만 어떻게 구현해야할지 헤멨던 문제

먼저 에라토스테네스의 체를 이용하여 소수를 판별해준 후
List에 판별한 소수들만을 담는다.
이 소수들이 우리가 사용할 수 있는 동전들이 된다.

이후 소수를 하나씩 꺼내 dp에 값을 1 증가시켜주고 (해당 값은 그 자체가 소수이므로)
n까지 증가시키며 dp에 값을 더해준다.
dp에 값이 1 이상있는 경우 해당 금액도 동전처럼 사용할 수 있게 된다.

ex) 2부터 8까지 반복하며
dp[4] += dp[2] (4원을 만들기 위해서 현재 2원 + 2원을 더한다)
dp[5] += dp[3] (5원을 만들기 위헤서 현재 2원 + 3원을 더한다)
dp[6] += dp[4] (6원을 만들기 위해서 현재 2원 + 4원을 더한다)
.
.
.
 */

public class Problem_16400 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int MOD = 123456789;
    static boolean[] isPrime;
    static ArrayList<Integer> primes = new ArrayList<>();
    static int[] dp;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        isPrime = new boolean[n + 1];
        dp = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = 2; i * j <= n; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        for (int curPrime : primes) {
            dp[curPrime]++;
            for (int j = curPrime; j <= n; j++) {
                dp[j] = (dp[j] % MOD + dp[j - curPrime] % MOD) % MOD;
            }
        }


        bw.write(String.valueOf(dp[n] % MOD));
        bw.flush();
        bw.close();
    }
}
