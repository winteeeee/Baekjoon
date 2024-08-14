package silver;

import java.io.*;
import java.util.StringTokenizer;

/*
패티가 치즈보다 많다면
햄버거를 하나 만드는 것(aba)으로 패티의 수를 하나 줄일 수 있다.
패티와 치즈의 차이가 1일 때 마지막 햄버거를 만들고 프로그램을 종료시키면 된다.
마지막 햄버거는 b의 수만큼 ab를 만들고 남은 패티 하나를 사용하기 위해 a를 끝에 붙여주면 된다.

많지 않다면 치즈버거를 만들 수 없으므로 NO를 출력한다.
 */

public class Problem_30020 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int count = 0;
        var sb = new StringBuilder();
        while (a != 0 && b != 0 && a > b) {
            if (a - b > 1) {
                sb.append("aba\n");
                a -= 2;
                b--;
            } else {
                for (int i = 0; i < b; i++) {
                    sb.append("ab");
                }
                sb.append("a");

                a = 0;
                b = 0;
            }
            count++;
        }

        if (a == 0 && b == 0) {
            bw.write("YES\n");
            bw.write(count + "\n");
            bw.write(sb.toString());
        } else {
            bw.write("NO");
        }

        bw.flush();
        bw.close();
    }
}
