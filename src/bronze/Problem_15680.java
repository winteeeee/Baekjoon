package bronze;

import java.io.*;

/*
매우 간단한 출력 문제
N을 입력받고 N의 값에 따라 다른 문자열을 출력하면 된다.
 */

public class Problem_15680 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        if (n == 0) {
            bw.write("YONSEI");
        } else {
            bw.write("Leading the Way to the Future");
        }

        bw.flush();
        bw.close();
    }
}
