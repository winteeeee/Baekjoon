package silver;

import java.io.*;

/*
모듈러 연산을 해야하는 것도 그렇고, 문제 생긴거부터가 전형적인 DP 문제다.
홀수 개수와 짝수 개수를 구해야하므로 홀수 테이블과 짝수 테이블을 따로 만들어둔 뒤
배열을 순회하며 테이블을 완성시킨 후 쿼리에 대한 답을 해주면 된다.

문제만 보면 동전 DP와 굉장히 유사한데 단순히 최댓값이 아닌 경우의 수이므로 수들의 조합이 아닌 순열을 구해야한다.
주어진 값들을 잘 관찰해보면 동전 DP를 그대로 따라가면 자연스럽게 수들의 순열이 구해지는 것을 알 수 있다.
수들의 순열을 모두 저장하면 메모리 초과가 날 수 있으므로 순열의 값을 테이블에 저장한다.
홀수 + 1 = 짝수, 짝수 + 1 = 홀수의 성질을 이용하여 1를 선택했을 때, 2를 선택했을 때, 3을 선택했을 때에 맞춰
적절히 이전 DP값을 가져와 더해주는 것으로 테이블으 완성시킬 수 있다.
 */

public class Problem_15993 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var sb = new StringBuilder();
        final int MOD = 1000000009;

        int t = Integer.parseInt(br.readLine());
        int[] even = new int[100001];
        int[] odd = new int[100001];

        for (int j = 1; j <= 100000; j++) {
            for (int k = 1; k < 4; k++) {
                if (j - k > 0) {
                    even[j] = (even[j] % MOD + odd[j - k] % MOD) % MOD;
                    odd[j] = (odd[j] % MOD + even[j - k] % MOD) % MOD;
                }

                if (k == j) {
                    odd[k] = (odd[k] % MOD + 1 % MOD) % MOD;
                }
            }
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(odd[n] % MOD).append(" ").append(even[n] % MOD).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
