package gold;

import java.io.*;
import java.util.StringTokenizer;

/*
1~N까지의 합을 A라고 하자
윤호가 x일 경우 동혁이는 A - x이다.
따라서 x + y이 A의 값으로 가능하지 않다면 -1이다.
A의 값으로 가능한지의 여부는 등차수열의 합 공식을 이용하여 간단히 구할 수 있다.

-1이 아니라면 윤호의 최소 승리 횟수를 구해야한다.
-1이 아니므로 1~N까지의 수를 적절히 배분하여 반드시 해당 값을 만들 수 있다.
N >= x일 경우 x번째 턴을 윤호가 단 한 번 이기는 것으로 성립시킬 수 있다. (단 x가 0인 경우 윤호가 승리하면 안되므로 따로 처리한다)
그게 아니라면 이 문제는 1~N까지의 수를 최대한 적게 이용하여 x를 만드는 문제로 바뀐다.
우리는 1~N까지의 수를 모두 사용할 수 있고 수를 적게 사용해야하므로 큰 수부터 그리디하게 선택하는 것으로 해당 수를 구할 수 있다.
 */

public class Problem_12934 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());
        long n = (long)Math.sqrt(2 * (x + y));

        if ((x == 0 && y == 0) || !(n * (n + 1) == 2 * (x + y))) {
            bw.write("-1");
        } else {
            if (n >= x) {
                bw.write(x != 0 ? "1" : "0");
            } else {
                long curValue = x;
                long result = 0;

                for (long i = n; curValue > 0; i--) {
                    curValue -= i;
                    result++;
                }
                bw.write(String.valueOf(result));
            }
        }

        bw.flush();
        bw.close();
    }
}
