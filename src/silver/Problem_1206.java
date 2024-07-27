package silver;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/*
소숫점이 잘리기 때문에 역산으로 접근하면 잘못된 답이 나온다.
풀이법이 명확하게 떠오르지 않았고 때문에 브루트포스의 느낌이 강하게 들었다.
소숫점 3자리에서 끊어지기 떄문에 답이 커봤자 1000 이하라고 생각했고 (엄밀하게 증명은 해보지 않음 아닐 수도?)
가능할 것 같은 느낌이 들어 브루트포스로 풀었다. 풀린 거보니 직관이 맞은 것같다.

질문 게시판을 보니 부동소수점 오차에 유의해야하는 것 같은데
자바의 BigDecimal은 기본적으로 부동소수점 오차를 생각하지 않아도 되기 때문에 그러한 문제가 생기지 않는다.
C++로 풀었으면 엄청 애먹었을 것 같은 문제
 */

public class Problem_1206 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        BigDecimal[] scores = new BigDecimal[n];
        for (int i = 0; i < n; i++) {
            BigDecimal temp = BigDecimal.valueOf(Double.parseDouble(br.readLine()));
            scores[i] = temp.setScale(3, RoundingMode.FLOOR);
        }
        Arrays.sort(scores);

        BigDecimal result = new BigDecimal(1);
        for (; true; result = result.add(new BigDecimal(1))) {
            BigDecimal maxSum = result.multiply(new BigDecimal(10));
            int idx = 0;

            for (int i = 0; i <= maxSum.intValue(); i++) {
                BigDecimal curVal = new BigDecimal(i).divide(result, 3, RoundingMode.FLOOR);
                if (curVal.equals(scores[idx])) {
                    while (curVal.equals(scores[idx])) {
                        idx++;

                        if (idx == n) {
                            bw.write(String.valueOf(result.intValue()));
                            bw.flush();
                            bw.close();

                            return;
                        }
                    }
                }
            }
        }
    }
}