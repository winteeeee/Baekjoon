package bronze;

import java.io.*;
import java.util.StringTokenizer;

/*
가로 기준으로 스티커를 붙였을 때 스티커의 좌/우로 1cm 이상의 공간이 남으려면
노트북의 너비 - 스티커의 너비가 1 초과여야 함을 알 수 있다.
정확히 하자면 두 값의 차이가 2 이상이여야 하지만 주어지는 값은 정수이므로
1 초과로 조건을 걸어도 모든 경우를 커버할 수 있다.
 */

public class Problem_21591 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int wc = Integer.parseInt(st.nextToken());
        int hc = Integer.parseInt(st.nextToken());
        int ws = Integer.parseInt(st.nextToken());
        int hs = Integer.parseInt(st.nextToken());

        if (wc - ws > 1 && hc - hs > 1) {
            bw.write("1");
        } else {
            bw.write("0");
        }

        bw.flush();
        bw.close();
    }
}
