package silver;

import java.io.*;
import java.util.StringTokenizer;

/*
루트를 기점으로 목표 노드까지 내려간다고 생각하면 선택지가 2개지만
목표 노드를 기점으로 루트로 올라간다고 생각하면 선택지는 1개이다(트리이므로)
따라서 목표 노드에서 루트까지 올라가며 카운팅을 해준다.

단 주어진 수의 범위가 커 선택지가 한쪽으로 치우쳐져 있을 경우 시간초과가 발생한다.
왼쪽 혹은 오른쪽 둘 중 하나의 카운팅이 끝나면 반복을 종료하고 바로 남은 수를 계산하는 방식으로
시간초과를 회피할 수 있다.
 */

public class Problem_2078 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int a;
    static int b;
    static int left;
    static int right;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void input() throws IOException {
        var st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
    }

    public static void solve() throws IOException {
        while (!(a == 1 || b == 1)) {
            if (a > b) {
                a -= b;
                left++;
            } else {
                b -= a;
                right++;
            }
        }
        left += a - 1;
        right += b - 1;

        bw.write(left + " " + right);
        bw.flush();
        bw.close();
    }
}
