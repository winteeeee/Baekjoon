package bronze;

import java.io.*;

/*
멀티탭과 멀티탭을 연결하는데 1개의 소켓이 소모되므로
주어진 멀티탭의 소켓 수를 모두 더 한 후
멀티탭끼리 연결되는 소켓의 수인 N - 1을 빼주면 된다.
 */

public class Problem_2010 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int result;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            result += Integer.parseInt(br.readLine());
        }
        result -= n - 1;

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
