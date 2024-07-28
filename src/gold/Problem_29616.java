package gold;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/*
이전 문제와 유사해보여 브루트포스로 접근했다가 시간초과로 실패
이 문제는 수학적으로 접근해야했다.

이전 시점의 총 투표 수는 백분율들의 최대공약수를 구한 후 백분율을 최대공약수로 나눈 몫들의 합이다.
현재 시점도 동일하게 구할 수 있지만 이점 시점의 투표 수보다 많아야 한다는 조건이 있으므로
백분율별로 투표 수를 따로 저장해둔 뒤 전수탐색하여 몇 배를 곱해야 이전 시점의 투표 수를 넘는지 구한 후
현재 시점의 투표 수에 배율을 곱하여 더해야 한다. (모든 수에 동일한 수가 곱해지므로 투표율은 변하지 않는다)

맞왜틀이 많이 발생했는데 오버플로우 이슈였다.
이전 시점의 경우 오버플로우가 절대 발생하지 않지만 현재 시점의 경우 이전 시점에서 배수로 곱해지므로
int 형의 범위를 벗어날 수 있다.
 */

public class Problem_29616 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        int[] prev = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            prev[i] = Integer.parseInt(st.nextToken());
        }

        int[] cur = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cur[i] = Integer.parseInt(st.nextToken());
        }

        int prevGcd = getGcd(prev);
        long[] prevCount = new long[n];
        long prevResult = 0;
        for (int i = 0; i < prev.length; i++) {
            prevCount[i] = (long) prev[i] / prevGcd;
            prevResult += (long) prevCount[i];
        }

        int curGcd = getGcd(cur);
        long[] curCount = new long[n];
        for (int i = 0; i < cur.length; i++) {
            curCount[i] = (long) cur[i] / curGcd;
        }

        long mul = 1;
        for (int i = 0; i < prevCount.length; i++) {
            if (prevCount[i] > curCount[i]) {
                BigDecimal prevDe = new BigDecimal(prevCount[i]);
                BigDecimal curDe = new BigDecimal(curCount[i]);
                BigDecimal curMul = prevDe.divide(curDe, 0, RoundingMode.UP);
                mul = Math.max(mul, curMul.intValue());
            }
        }

        long curResult = 0;
        for (int i = 0; i < curCount.length; i++) {
            curCount[i] *= mul;
            curResult += (long) curCount[i];
        }

        bw.write(prevResult + " " + curResult);
        bw.flush();
        bw.close();
    }

    public static int getGcd(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        BigInteger a = new BigInteger(String.valueOf(arr[0]));
        BigInteger b = new BigInteger(String.valueOf(arr[1]));
        BigInteger gcd = a.gcd(b);

        for (int i = 2; i < arr.length; i++) {
            gcd = gcd.gcd(new BigInteger(String.valueOf(arr[i])));
        }

        return gcd.intValue();
    }
}
